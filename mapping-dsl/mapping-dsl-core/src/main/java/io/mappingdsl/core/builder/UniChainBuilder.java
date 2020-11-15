package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class UniChainBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> initialExpressionBuilder;

    public UniChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.initialExpressionBuilder = new UniInitialExpressionBuilder<>(this.context);
    }

    public UniInitialTypeBuilder uniMapping() {
        registerCurrentRule();
        return new UniInitialTypeBuilder(this.context);
    }

    // delegate method
    public <SRC_TYPE> UniConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> UniTerminalWrapperRouterExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslHost<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    public MappingDsl build() {
        registerCurrentRule();
        return new MappingDsl(this.context.getConfiguration(), this.context.getMappingRules());
    }

    private void registerCurrentRule() {
        this.context.addMappingRule(mappingRule);
    }

}
