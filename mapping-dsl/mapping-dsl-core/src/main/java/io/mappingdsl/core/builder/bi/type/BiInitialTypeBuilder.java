package io.mappingdsl.core.builder.bi.type;

import io.mappingdsl.core.MappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class BiInitialTypeBuilder {

    private final MappingContext<?, ?> context;

    public <SRC_ROOT> BiTerminalTypeBuilder<SRC_ROOT> between(Class<SRC_ROOT> initialType) {
        return new BiTerminalTypeBuilder<>(this.context, initialType);
    }

}
