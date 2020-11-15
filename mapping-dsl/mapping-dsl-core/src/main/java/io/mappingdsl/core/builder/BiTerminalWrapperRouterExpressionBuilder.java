package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalWrapperRouterExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

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

}
