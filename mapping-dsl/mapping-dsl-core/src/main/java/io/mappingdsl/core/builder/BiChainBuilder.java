package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class BiChainBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> initialExpressionBuilder;

    public BiChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.initialExpressionBuilder = new BiInitialExpressionBuilder<>(this.context);
    }

    public BiInitialTypeBuilder biMapping() {
        registerCurrentRule();
        return new BiInitialTypeBuilder(this.context);
    }

    // delegate method
    public <SRC_TYPE> BiConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiTerminalWrapperRouterExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.bind(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiConvertedExpressionConsumerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.supply(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> BiConvertedExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

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
