package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.AbstractDslExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public <TRG_TYPE> BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
            DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new BiMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public <TRG_TYPE> BiDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
            AbstractDslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new BiDslExpressionHintsBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingHint(Class<? extends TRG_TYPE> hint) {
            return new BiProducerMappingConditionBuilder<>(this.context, this.mappingRule.withTerminalHint(hint));
        }

        public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingGlobalHint() {
            return new BiProducerMappingConditionBuilder<>(this.context, this.mappingRule);
        }

    }

}
