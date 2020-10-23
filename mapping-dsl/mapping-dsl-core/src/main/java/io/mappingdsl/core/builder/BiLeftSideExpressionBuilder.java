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

    public <SRC_TYPE> BiRightSideValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> leftSideExpression) {

        return new BiRightSideValueExpressionBuilder<>(
                this.mappingKey, new MappingRule<>(MappingRuleDirection.BOTH, leftSideExpression), this.mappingRules);
    }

    public <SRC_TYPE> BiValueExpressionConsumerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> sourceExpression) {

        return new BiValueExpressionConsumerBuilder<>(
                this.mappingKey, new MappingRule<>(MappingRuleDirection.FORWARD, sourceExpression), this.mappingRules);
    }

    public <SRC_TYPE> BiValueExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> sourceExpression) {

        return new BiValueExpressionProducerBuilder<>(
                this.mappingKey, new MappingRule<>(MappingRuleDirection.BACKWARD, sourceExpression), this.mappingRules);
    }

}
