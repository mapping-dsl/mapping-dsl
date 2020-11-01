package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import lombok.experimental.Delegate;

public class BiConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    @Delegate
    private final BiTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public BiConvertedExpressionBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new BiTerminalExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> BiTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverters(
            Converter<SRC_TYPE, NEW_SRC_TYPE> initialExpressionConverter,
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalExpressionBuilder<>(this.context, rule);
    }

    public <NEW_SRC_TYPE> BiTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            BiConverter<SRC_TYPE, NEW_SRC_TYPE> converters) {

        Converter<SRC_TYPE, NEW_SRC_TYPE> forwardConverter = converters::convertForward;
        Converter<NEW_SRC_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(forwardConverter)
                .withTerminalExpressionConverter(backwardConverter);

        return new BiTerminalExpressionBuilder<>(this.context, rule);
    }

}
