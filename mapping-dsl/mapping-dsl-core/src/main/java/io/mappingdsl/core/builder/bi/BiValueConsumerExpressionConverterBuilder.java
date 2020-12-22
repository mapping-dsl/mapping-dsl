package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class BiValueConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiTerminalValueConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> terminalExpressionBuilder;

    BiValueConsumerExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new BiTerminalValueConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalValueConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalValueConsumerExpressionBuilder<>(this.context, rule);
    }

    // delegate method
    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return this.terminalExpressionBuilder.from(targetExpression);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalValueConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                ValueExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
