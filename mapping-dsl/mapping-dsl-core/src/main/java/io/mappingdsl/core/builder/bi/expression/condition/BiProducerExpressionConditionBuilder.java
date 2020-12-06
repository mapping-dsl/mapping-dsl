package io.mappingdsl.core.builder.bi.expression.condition;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.bi.expression.BiExpressionChainBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiConsumerExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiProducerExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiConsumerWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiProducerWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.type.BiTypeInitiatorBuilder;
import io.mappingdsl.core.common.Condition;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class BiProducerExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> chainBuilder;

    public BiProducerExpressionConditionBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.chainBuilder = new BiExpressionChainBuilder<>(this.context, this.mappingRule);
    }

    public BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> when(Condition<SRC_TYPE> condition) {
        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialCondition(condition).withTerminalCondition(null);

        return new BiExpressionChainBuilder<>(this.context, rule);
    }

    // delegate method
    public BiTypeInitiatorBuilder biMapping() {
        return this.chainBuilder.biMapping();
    }

    // delegate method
    public <NEW_SRC_TYPE> BiExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiWrapperExpressionTerminatorBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            DslHost<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiProducerWrapperExpressionTerminatorBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            DslHost<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiConsumerWrapperExpressionTerminatorBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            DslHost<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    public MappingDsl build() {
        return this.chainBuilder.build();
    }

}
