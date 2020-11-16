package io.mappingdsl.core.builder.bi.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.builder.bi.expression.converter.BiConsumerExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiProducerExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiConsumerWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiProducerWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiExpressionInitiatorBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;

    public <SRC_TYPE> BiExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiWrapperExpressionTerminatorBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiProducerWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiProducerWrapperExpressionTerminatorBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiConsumerWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiConsumerWrapperExpressionTerminatorBuilder<>(this.context, mappingRule);
    }

}
