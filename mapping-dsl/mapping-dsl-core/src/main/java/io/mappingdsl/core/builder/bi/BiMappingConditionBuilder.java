package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.uni.UniInitialTypeBuilder;
import io.mappingdsl.core.common.BiCondition;
import io.mappingdsl.core.common.Condition;
import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.ValueCollectionExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public final class BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> chainBuilder;

    BiMappingConditionBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.chainBuilder = new BiExpressionChainBuilder<>(this.context, this.mappingRule);
    }

    public BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> when(
            Condition<SRC_TYPE> forwardCondition, Condition<TRG_TYPE> backwardCondition) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialCondition(forwardCondition).withTerminalCondition(backwardCondition);

        return new BiExpressionChainBuilder<>(this.context, rule);
    }

    public BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> when(BiCondition<SRC_TYPE, TRG_TYPE> condition) {
        Condition<SRC_TYPE> forwardCondition = condition::testForward;
        Condition<TRG_TYPE> backwardCondition = condition::testBackward;

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialCondition(forwardCondition).withTerminalCondition(backwardCondition);

        return new BiExpressionChainBuilder<>(this.context, rule);
    }

    // delegate method
    public UniInitialTypeBuilder uniMapping() {
        return this.chainBuilder.uniMapping();
    }

    // delegate method
    public BiInitialTypeBuilder biMapping() {
        return this.chainBuilder.biMapping();
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            ValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            DslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            DslCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            ValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            DslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            DslCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            DslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            DslCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    public MappingDsl build() {
        return this.chainBuilder.build();
    }

}
