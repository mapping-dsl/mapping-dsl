package io.mappingdsl.core.builder.bi.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalCompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> asIs() {
        return new BiTerminalCompatibleWrapperExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> BiTerminalCompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, NEW_SRC_TYPE> usingConverters(
            Converter<SRC_TYPE, NEW_SRC_TYPE> initialExpressionConverter,
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalCompatibleWrapperExpressionBuilder<>(this.context, rule);
    }

    public <NEW_SRC_TYPE> BiTerminalCompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, NEW_SRC_TYPE> usingConverter(
            BiConverter<SRC_TYPE, NEW_SRC_TYPE> converters) {

        Converter<SRC_TYPE, NEW_SRC_TYPE> forwardConverter = converters::convertForward;
        Converter<NEW_SRC_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        return usingConverters(forwardConverter, backwardConverter);
    }

    public BiTerminalIncompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleWrapperExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor
    public static class BiTerminalCompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslHost<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor
    public static class BiTerminalIncompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslHost<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
