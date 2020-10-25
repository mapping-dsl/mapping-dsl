package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiLeftSideExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRules mappingRules;

    public <SRC_TYPE> BiRightSideValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> leftSideExpression) {

        MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> mappingRule = new MappingRule<>(
                MappingRuleDirection.BOTH, leftSideExpression, null);

        return new BiRightSideValueExpressionBuilder<>(this.mappingKey, mappingRule, this.mappingRules);
    }

    public <SRC_TYPE> BiValueExpressionConsumerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> supply(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> sourceExpression) {

        MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> mappingRule = new MappingRule<>(
                MappingRuleDirection.FORWARD, sourceExpression, null);

        return new BiValueExpressionConsumerBuilder<>(this.mappingKey, mappingRule, this.mappingRules);
    }

    public <SRC_TYPE> BiValueExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> sourceExpression) {

        MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> mappingRule = new MappingRule<>(
                MappingRuleDirection.BACKWARD, sourceExpression, null);

        return new BiValueExpressionProducerBuilder<>(this.mappingKey, mappingRule, this.mappingRules);
    }

}
