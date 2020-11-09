package io.mappingdsl.core.execution;

import ice.bricks.reflection.ReflectionUtils;
import io.mappingdsl.core.MappingConfiguration;
import io.mappingdsl.core.MappingConfiguration.MissingMappingHandlingMode;
import io.mappingdsl.core.MappingConfiguration.NullHandlingMode;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.builder.Condition;
import io.mappingdsl.core.builder.Converter;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.TargetPathTraverserFunction;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

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
            if (this.mappingConfiguration.getMissingMappingHandlingMode() == MissingMappingHandlingMode.TERMINATE) {
                throw new NoMappingException(mappingKey);
            }
            else {
                return null;
            }
        }

        TRG target = ReflectionUtils.generateNewInstance(targetType);

        for (MappingRule<?, ?> rule : rules) {
            Deque<ExpressionBase<?, ?, ?>> sourcePath = unwindPath(rule.getInitialExpression());
            Object sourceValue = produceValue(source, sourcePath);
            if (sourceValue == null) {
                continue;
            }

            Converter<Object, Object> expressionConverter =
                    (Converter<Object, Object>) rule.getInitialExpressionConverter();

            if (expressionConverter != null) {
                sourceValue = expressionConverter.convert(sourceValue);
            }

            Deque<ExpressionBase<?, ?, ?>> targetPath = unwindPath(rule.getTerminalExpression());
            consumeValue(rule, sourceValue, target, targetPath);
        }

        return target;
    }

    private Deque<ExpressionBase<?, ?, ?>> unwindPath(ExpressionBase<?, ?, ?> expression) {
        Deque<ExpressionBase<?, ?, ?>> expressionsPath = new LinkedList<>();
        ExpressionBase<?, ?, ?> currentExpression = expression;

        while (currentExpression.getExpressionFunction().getClass() != RootIdentityFunction.class) {
            expressionsPath.push(currentExpression);
            currentExpression = currentExpression.getParentExpression();
        }

        return expressionsPath;
    }

    private Object produceValue(Object source, Deque<ExpressionBase<?, ?, ?>> path) {
        Object value = source;

        while (!path.isEmpty()) {
            ExpressionBase<?, ?, ?> expression = path.pop();
            ValueProducerFunction producerFunction = (ValueProducerFunction) expression.getExpressionFunction();
            value = producerFunction.produce(value);

            if (value == null && !path.isEmpty()) {
                // based on null-handling configuration
                return getNullSourceValue(expression);
            }
        }

        return value;
    }

    private void consumeValue(MappingRule<?, ?> rule, Object source, Object target, Deque<ExpressionBase<?, ?, ?>> path) {
        Object currentTarget = target;

        while (!path.isEmpty()) {
            ExpressionBase<?, ?, ?> expression = path.pop();

            if (path.isEmpty()) {
                ValueConsumerFunction consumerFunction = (ValueConsumerFunction) expression.getExpressionFunction();

                Condition<Object, Object> condition = (Condition<Object, Object>) rule.getInitialCondition();
                if (condition == null || condition.test(source, consumerFunction.getConsumer(currentTarget))) {
                    Class<?> targetType = consumerFunction.getConsumerType();

                    // check if nested mapping is required
                    MappingKey<?, ?> nestedMappingKey = new MappingKey<>(source.getClass(), targetType);
                    if (this.mappingRules.containsMappingRules(nestedMappingKey)) {
                        source = executeMapping(source, targetType);
                    }

                    consumerFunction.consume(currentTarget, source);
                }
            }
            else {
                TargetPathTraverserFunction traverserFunction =
                        (TargetPathTraverserFunction) expression.getExpressionFunction();

                currentTarget = traverserFunction.step(currentTarget);
            }
        }
    }

    private Object getNullSourceValue(ExpressionBase<?, ?, ?> expression) {
        if (this.mappingConfiguration.getNullHandlingMode() == NullHandlingMode.PROCEED) {
            return null;
        }
        else {
            throw new NullSourceValueException(expression);
        }
    }

}
