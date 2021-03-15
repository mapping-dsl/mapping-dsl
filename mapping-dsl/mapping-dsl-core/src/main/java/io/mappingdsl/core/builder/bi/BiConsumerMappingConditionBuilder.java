package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.uni.UniInitialTypeBuilder;
import io.mappingdsl.core.common.Condition;
import io.mappingdsl.core.expression.array.AbstractValueArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.array.ValueArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractValueCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.collection.ValueCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.AbstractValueExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import io.mappingdsl.core.expression.simple.ValueExpression;

public final class BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> chainBuilder;

    BiConsumerMappingConditionBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.chainBuilder = new BiExpressionChainBuilder<>(this.context, this.mappingRule);
    }

    public BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> when(Condition<TRG_TYPE> condition) {
        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialCondition(null).withTerminalCondition(condition);

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
    public <NEW_SRC_TYPE> BiValueExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            AbstractValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            ValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            ValueArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            AbstractValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            AbstractValueArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            DslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiAbstractDslExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            AbstractDslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            DslCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            DslArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            AbstractValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            ValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            ValueArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            AbstractValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            AbstractValueArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            DslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            AbstractDslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            DslCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> produce(
            DslArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.produce(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            AbstractValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            AbstractValueCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            AbstractValueArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            DslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiAbstractDslConsumerExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            AbstractDslExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            DslCollectionExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    // delegate method
    public <NEW_SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            DslArrayExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    public MappingDsl build() {
        return this.chainBuilder.build();
    }

}
