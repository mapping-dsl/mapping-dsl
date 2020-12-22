package io.mappingdsl.core.builder.uni;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class UniValueExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniTerminalValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> terminalExpressionBuilder;

    UniValueExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new UniTerminalValueExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> UniTerminalValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> converter) {

        return new UniTerminalValueExpressionBuilder<>(
                this.context, this.mappingRule.withInitialExpressionConverter(converter));
    }

    // delegate method
    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniTerminalValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                ValueExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
