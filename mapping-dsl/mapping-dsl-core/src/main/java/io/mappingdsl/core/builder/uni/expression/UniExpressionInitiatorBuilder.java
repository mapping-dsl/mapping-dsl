package io.mappingdsl.core.builder.uni.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.expression.DslHostExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniExpressionInitiatorBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;

    public <SRC_TYPE> UniExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniExpressionWrapperTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslHostExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniExpressionWrapperTerminatorBuilder<>(this.context, mappingRule);
    }

}
