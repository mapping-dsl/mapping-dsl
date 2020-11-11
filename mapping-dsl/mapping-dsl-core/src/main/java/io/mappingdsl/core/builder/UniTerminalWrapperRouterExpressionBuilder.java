package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTerminalWrapperRouterExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public UniTerminalIncompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new UniTerminalIncompatibleWrapperExpressionBuilder<>(this.context, this.mappingRule);
    }

    public UniTerminalCompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> asIs() {
        return new UniTerminalCompatibleWrapperExpressionBuilder<>(this.context, this.mappingRule);
    }

}
