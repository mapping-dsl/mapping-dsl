package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiAbstractDslConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalIncompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> usingHint(
            Class<? extends SRC_TYPE> hint) {

        return new BiTerminalIncompatibleDslConsumerExpressionBuilder<>(
                this.context, this.mappingRule.withInitialHint(hint));
    }

    public BiTerminalIncompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> usingGlobalHint() {
        return new BiTerminalIncompatibleDslConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleDslConsumerExpressionBuilder<>(this.context, rule);
    }

}
