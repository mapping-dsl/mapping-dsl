package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import lombok.experimental.Delegate;

public class BiConvertedExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    @Delegate
    private final BiTerminalExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public BiConvertedExpressionProducerBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;

        this.terminalExpressionBuilder = new BiTerminalExpressionProducerBuilder<>(
                this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> BiTerminalExpressionProducerBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalExpressionProducerBuilder<>(this.context, rule);
    }

}
