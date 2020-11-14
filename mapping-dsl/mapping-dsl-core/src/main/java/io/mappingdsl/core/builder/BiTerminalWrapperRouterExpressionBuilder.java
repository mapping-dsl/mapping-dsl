package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalWrapperRouterExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalIncompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleWrapperExpressionBuilder<>(this.context, this.mappingRule);
    }

}
