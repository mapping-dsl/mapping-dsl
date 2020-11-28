package io.mappingdsl.core.builder.bi.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.bi.expression.converter.BiConsumerExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.converter.BiProducerExpressionConverterBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiConsumerWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiProducerWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.wrapper.BiWrapperExpressionTerminatorBuilder;
import io.mappingdsl.core.builder.bi.type.BiTypeInitiatorBuilder;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class BiExpressionChainBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiExpressionInitiatorBuilder<SRC_ROOT, TRG_ROOT> initialExpressionBuilder;

    public BiExpressionChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.initialExpressionBuilder = new BiExpressionInitiatorBuilder<>(this.context);
    }

    public BiTypeInitiatorBuilder biMapping() {
        registerCurrentRule();
        return new BiTypeInitiatorBuilder(this.context);
    }

    // delegate method
    public <SRC_TYPE> BiExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiProducerWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.consume(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiConsumerWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

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
