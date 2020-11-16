package io.mappingdsl.core.builder.bi.type;

import io.mappingdsl.core.MappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTypeInitiatorBuilder {

    private final MappingContext<?, ?> context;

    public <SRC_ROOT> BiTypeTerminatorBuilder<SRC_ROOT> between(Class<SRC_ROOT> initialType) {
        return new BiTypeTerminatorBuilder<>(this.context, initialType);
    }

}
