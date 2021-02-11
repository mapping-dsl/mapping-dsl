package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.AbstractValueExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class BiValueProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiTerminalValueProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> terminalExpressionBuilder;

    BiValueProducerExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new BiTerminalValueProducerExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalValueProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withInitialExpressionConverter(converter);
        return new BiTerminalValueProducerExpressionBuilder<>(this.context, rule);
    }

    // delegate method
    public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    // delegate method
    public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            AbstractValueExpression<TRG_ROOT, ? super SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalValueProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                ValueExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiProducerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                AbstractValueExpression<TRG_ROOT, ? super TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiProducerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
