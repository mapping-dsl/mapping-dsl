package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import lombok.experimental.Delegate;

public class UniConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    @Delegate
    private final UniTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public UniConvertedExpressionBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new UniTerminalExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> UniTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            Converter<SRC_TYPE, NEW_SRC_TYPE> converter) {

        return new UniTerminalExpressionBuilder<>(
                this.context, this.mappingRule.withInitialExpressionConverter(converter));
    }

}
