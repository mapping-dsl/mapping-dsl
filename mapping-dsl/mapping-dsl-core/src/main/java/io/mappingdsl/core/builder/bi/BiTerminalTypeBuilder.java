package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingKey;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiTerminalTypeBuilder<SRC_ROOT> {

    private final MappingContext<?, ?> context;
    private final Class<SRC_ROOT> sourceType;

    public <TRG_ROOT> BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> and(Class<TRG_ROOT> terminalType) {
        MappingKey<SRC_ROOT, TRG_ROOT> mappingKey = new MappingKey<>(this.sourceType, terminalType);
        return new BiInitialExpressionBuilder<>(this.context.withMappingKey(mappingKey));
    }

}
