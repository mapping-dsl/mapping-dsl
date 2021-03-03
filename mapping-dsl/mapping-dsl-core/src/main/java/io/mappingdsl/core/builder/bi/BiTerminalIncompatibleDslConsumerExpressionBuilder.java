package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiTerminalIncompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public <TRG_TYPE> BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
            DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public <TRG_TYPE> BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
            AbstractDslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
