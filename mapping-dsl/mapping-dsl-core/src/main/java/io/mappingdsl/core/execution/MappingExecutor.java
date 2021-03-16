package io.mappingdsl.core.execution;

import ice.bricks.reflection.ReflectionUtils;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.common.Condition;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.config.BeanFactoryConfiguration;
import io.mappingdsl.core.config.MappingConfiguration;
import io.mappingdsl.core.config.MissingMappingHandlingMode;
import io.mappingdsl.core.config.NullHandlingMode;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.TargetPathTraverserFunction;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class MappingExecutor {

    private final MappingConfiguration mappingConfiguration;
    private final MappingRules mappingRules;

    public <SRC, TRG> TRG executeMapping(SRC source, Class<TRG> targetType) {
        if (source == null) {
            // based on null-handling configuration
            return (TRG) getNullSourceValue(null);
        }

        Class<SRC> sourceType = (Class<SRC>) source.getClass();
        MappingKey<SRC, TRG> mappingKey = new MappingKey<>(sourceType, targetType);
        Set<MappingRule<?, ?>> rules = this.mappingRules.getMappingRules(mappingKey);

        if (CollectionUtils.isEmpty(rules)) {
            if (this.mappingConfiguration.getMissingMappingHandlingMode() == MissingMappingHandlingMode.RETURN_NULL) {
                return null;
            }
            else {
                throw new NoMappingException(mappingKey);
            }
        }

        TRG target;

        BeanFactoryConfiguration beanFactoryConfiguration = this.mappingConfiguration.getBeanFactoryConfiguration();
        if (beanFactoryConfiguration.hasFactory(targetType)) {
            MappingBeanFactory<TRG> beanFactory = beanFactoryConfiguration.getFactory(targetType);
            target = beanFactory.create(source, targetType);
        }
        else {
            target = ReflectionUtils.generateNewInstance(targetType);
        }

        for (MappingRule<?, ?> rule : rules) {
            Deque<ExpressionBase<?, ?>> sourcePath = unwindPath(rule.getInitialExpression());
            Object sourceValue = produceValue(source, sourcePath);
            if (sourceValue == null) {
                continue;
            }

            Stream<Object> sourceValues = splitSourceValue(sourceValue);
            Condition<Object> condition = (Condition<Object>) rule.getInitialCondition();
            Converter<Object, Object> converter = (Converter<Object, Object>) rule.getInitialExpressionConverter();

            Deque<ExpressionBase<?, ?>> targetPath = unwindPath(rule.getTerminalExpression());
            ValueConsumer valueConsumer = getValueConsumer(target, targetPath);

            Stream<Object> mappedValues = sourceValues
                    .filter(Objects::nonNull)
                    .filter(value -> condition == null || condition.test(value))
                    .map(value -> (converter == null) ? value : converter.convert(value))
                    .map(value -> mapValue(rule, valueConsumer, value));

            ValueConsumerFunction consumerFunction = valueConsumer.consumerFunction;
            if (consumerFunction.collectionConsumer()) {
                consumerFunction.consume(valueConsumer.target, mappedValues);
            }
            else {
                mappedValues.forEach(targetValue -> consumerFunction.consume(valueConsumer.target, targetValue));
            }
        }

        return target;
    }

    private Object mapValue(MappingRule<?, ?> rule, ValueConsumer valueConsumer, Object sourceValue) {
        ValueConsumerFunction consumerFunction = valueConsumer.consumerFunction;
        Class<?> targetType = consumerFunction.getConsumerType();

        // check if nested mapping is required
        MappingKey<?, ?> nestedMappingKey = new MappingKey<>(sourceValue.getClass(), targetType);
        if (this.mappingRules.containsMappingRules(nestedMappingKey)) {
            sourceValue = executeMapping(sourceValue, targetType);
        }

        // check if hint and related mapping are defined
        Class<?> targetHintType = rule.getTerminalHint();
        if (targetHintType == null) {
            targetHintType = this.mappingConfiguration.getHintConfiguration().getHint(targetType);
        }

        if (targetHintType != null) {
            MappingKey<?, ?> hintMappingKey = new MappingKey<>(sourceValue.getClass(), targetHintType);
            if (this.mappingRules.containsMappingRules(hintMappingKey)) {
                sourceValue = executeMapping(sourceValue, targetHintType);
            }
        }

        // fail if types are incompatible
        if (!consumerFunction.canConsume(sourceValue.getClass())) {
            throw new IllegalAssignmentException(consumerFunction, sourceValue.getClass());
        }

        return sourceValue;
    }

    private Stream<Object> splitSourceValue(Object sourceValue) {
        if (sourceValue instanceof Iterable) {
            return StreamSupport.stream(((Iterable<Object>) sourceValue).spliterator(), false);
        }

        if (sourceValue.getClass().isArray()) {
            return Arrays.stream((Object[]) sourceValue);
        }

        return Stream.of(sourceValue);
    }

    private Deque<ExpressionBase<?, ?>> unwindPath(ExpressionBase<?, ?> expression) {
        Deque<ExpressionBase<?, ?>> expressionsPath = new LinkedList<>();
        ExpressionBase<?, ?> currentExpression = expression;

        while (currentExpression.getExpressionFunction().getClass() != RootIdentityFunction.class) {
            expressionsPath.push(currentExpression);
            currentExpression = currentExpression.getParentExpression();
        }

        return expressionsPath;
    }

    private Object produceValue(Object source, Deque<ExpressionBase<?, ?>> path) {
        Object value = source;

        while (!path.isEmpty()) {
            ExpressionBase<?, ?> expression = path.pop();
            ValueProducerFunction producerFunction = (ValueProducerFunction) expression.getExpressionFunction();
            value = producerFunction.produce(value);

            if (value == null && !path.isEmpty()) {
                // based on null-handling configuration
                return getNullSourceValue(expression);
            }
        }

        return value;
    }

    private ValueConsumer getValueConsumer(Object target, Deque<ExpressionBase<?, ?>> path) {
        Object currentTarget = target;

        while (!path.isEmpty()) {
            ExpressionBase<?, ?> expression = path.pop();

            if (path.isEmpty()) {
                ValueConsumerFunction consumerFunction = (ValueConsumerFunction) expression.getExpressionFunction();
                return new ValueConsumer(currentTarget, consumerFunction);
            }
            else {
                TargetPathTraverserFunction traverserFunction =
                        (TargetPathTraverserFunction) expression.getExpressionFunction();

                currentTarget = traverserFunction.step(currentTarget);
            }
        }

        throw new IllegalStateException("Unable to get value consumer");
    }

    private Object getNullSourceValue(ExpressionBase<?, ?> expression) {
        if (this.mappingConfiguration.getNullHandlingMode() == NullHandlingMode.PROCEED) {
            return null;
        }
        else {
            throw new NullSourceValueException(expression);
        }
    }

    @RequiredArgsConstructor
    private static class ValueConsumer {

        private final Object target;
        private final ValueConsumerFunction consumerFunction;

    }

}
