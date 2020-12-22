package io.mappingdsl.core.builder.uni.type;

import io.mappingdsl.core.MappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UniInitialTypeBuilder {

    private final MappingContext<?, ?> context;

    public <SRC_ROOT> UniTerminalTypeBuilder<SRC_ROOT> from(Class<SRC_ROOT> initialType) {
        return new UniTerminalTypeBuilder<>(this.context, initialType);
    }

}
