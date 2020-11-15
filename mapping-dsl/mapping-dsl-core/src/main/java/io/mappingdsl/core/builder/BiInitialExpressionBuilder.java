package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;

    public <SRC_TYPE> BiConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiConvertedExpressionBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiTerminalWrapperRouterExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiTerminalWrapperRouterExpressionBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiConvertedExpressionConsumerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiConvertedExpressionConsumerBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiTerminalWrapperRouterProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiTerminalWrapperRouterProducerExpressionBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiConvertedExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiConvertedExpressionProducerBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiTerminalWrapperRouterConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiTerminalWrapperRouterConsumerExpressionBuilder<>(this.context, mappingRule);
    }

}
