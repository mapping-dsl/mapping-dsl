package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiAbstractDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingHint(
            Class<? extends SRC_TYPE> hint) {

        return new BiTerminalIncompatibleDslExpressionBuilder<>(
                this.context, this.mappingRule.withInitialHint(hint));
    }

    public BiTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingGlobalHint() {
        return new BiTerminalIncompatibleDslExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleDslExpressionBuilder<>(this.context, rule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverters(
            Converter<SRC_TYPE, TRG_TYPE> initialExpressionConverter,
            Converter<TRG_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalCompatibleDslExpressionBuilder<>(this.context, rule);
    }

}
