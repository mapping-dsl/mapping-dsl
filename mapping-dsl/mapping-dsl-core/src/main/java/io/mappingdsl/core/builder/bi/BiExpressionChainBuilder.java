package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.uni.UniInitialTypeBuilder;
import io.mappingdsl.core.expression.DslArrayExpression;
import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.ValueArrayExpression;
import io.mappingdsl.core.expression.ValueCollectionExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public final class BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> initialExpressionBuilder;

    BiExpressionChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.initialExpressionBuilder = new BiInitialExpressionBuilder<>(this.context);
    }

    public UniInitialTypeBuilder uniMapping() {
        registerCurrentRule();
        return new UniInitialTypeBuilder(this.context);
    }

    public BiInitialTypeBuilder biMapping() {
        registerCurrentRule();
        return new BiInitialTypeBuilder(this.context);
    }

    // delegate method
    public <SRC_TYPE> BiValueExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    public MappingDsl build() {
        registerCurrentRule();
        return new MappingDsl(this.context.getConfiguration(), this.context.getMappingRules());
    }

    private void registerCurrentRule() {
        this.context.addMappingRule(this.mappingRule);
    }

}
