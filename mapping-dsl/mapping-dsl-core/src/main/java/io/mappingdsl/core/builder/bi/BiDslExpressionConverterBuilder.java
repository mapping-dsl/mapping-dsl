package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiDslExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> asIs() {
        return new BiTerminalCompatibleDslExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverters(
            Converter<SRC_TYPE, TRG_TYPE> initialExpressionConverter,
            Converter<TRG_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalCompatibleDslExpressionBuilder<>(this.context, rule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            BiConverter<SRC_TYPE, TRG_TYPE> converters) {

        Converter<SRC_TYPE, TRG_TYPE> forwardConverter = converters::convertForward;
        Converter<TRG_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        return usingConverters(forwardConverter, backwardConverter);
    }

    public BiTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleDslExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
