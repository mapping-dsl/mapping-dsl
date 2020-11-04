package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniChainBuilder<SRC_ROOT, TRG_ROOT> chainBuilder;

    public UniMappingConditionBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.chainBuilder = new UniChainBuilder<>(context, mappingRule);
    }

    public UniChainBuilder<SRC_ROOT, TRG_ROOT> when(Condition<SRC_TYPE, TRG_TYPE> condition) {
        return new UniChainBuilder<>(this.context, this.mappingRule.withInitialCondition(condition));
    }

    // delegate method, to make the path: .to(...).supply(...)
    public <NEW_SRC_TYPE> UniConvertedExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.supply(initialExpression);
    }

    // delegate method, to make the path: .to(...).build()
    public MappingDsl build() {
        return this.chainBuilder.build();
    }

}
