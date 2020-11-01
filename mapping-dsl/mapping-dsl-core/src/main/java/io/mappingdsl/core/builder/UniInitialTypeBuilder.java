package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniInitialTypeBuilder {

    private final MappingContext<?, ?> context;

    public <SRC_ROOT> UniTerminalTypeBuilder<SRC_ROOT> from(Class<SRC_ROOT> sourceType) {
        return new UniTerminalTypeBuilder<>(this.context, sourceType);
    }

}
