package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class UniChainBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> initialExpressionBuilder;

    public UniChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.initialExpressionBuilder = new UniInitialExpressionBuilder<>(this.context);

        this.context.addMappingRule(mappingRule);
    }

    // delegate method
    public <SRC_TYPE> UniConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.initialExpressionBuilder.supply(initialExpression);
    }

    public MappingDsl build() {
        return new MappingDsl(this.context.getConfiguration(), this.context.getMappingRules());
    }

}
