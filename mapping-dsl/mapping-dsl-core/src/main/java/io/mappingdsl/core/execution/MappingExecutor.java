package io.mappingdsl.core.execution;

import ice.bricks.reflection.ReflectionUtils;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.builder.UniMappingRule;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.TargetPathTraverserFunction;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

@RequiredArgsConstructor
public class MappingExecutor {

    private final MappingRules mappingRules;

    public <SRC, TRG> TRG executeMapping(SRC source, Class<TRG> targetType) {
        if (source == null) {
            return null;
        }

        Class<SRC> sourceType = (Class<SRC>) source.getClass();
        MappingKey<SRC, TRG> mappingKey = new MappingKey<>(sourceType, targetType);
        Set<UniMappingRule<?, ?, ?, ?>> rules = this.mappingRules.getMappingRules(mappingKey);

        TRG target = ReflectionUtils.generateNewInstance(targetType);

        for (UniMappingRule<?, ?, ?, ?> rule : rules) {
            Deque<ExpressionBase<?, ?, ?>> sourcePath = unwindPath(rule.getSourceExpression());
            Object sourceValue = produceValue(source, sourcePath);
            if (sourceValue == null) {
                continue;
            }

            Deque<ExpressionBase<?, ?, ?>> targetPath = unwindPath(rule.getTargetExpression());
            consumeValue(sourceValue, target, targetPath);
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
        }

        return value;
    }

    private void consumeValue(Object source, Object target, Deque<ExpressionBase<?, ?, ?>> path) {
        Object currentTarget = target;

        while (!path.isEmpty()) {
            ExpressionBase<?, ?, ?> expression = path.pop();

            if (path.isEmpty()) {
                ValueConsumerFunction consumerFunction = (ValueConsumerFunction) expression.getExpressionFunction();
                consumerFunction.consume(currentTarget, source);
            }
            else {
                TargetPathTraverserFunction traverserFunction =
                        (TargetPathTraverserFunction) expression.getExpressionFunction();

                currentTarget = traverserFunction.step(currentTarget);
            }
        }
    }

}