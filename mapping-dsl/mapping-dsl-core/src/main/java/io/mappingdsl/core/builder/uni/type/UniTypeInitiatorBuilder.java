package io.mappingdsl.core.builder.uni.type;

import io.mappingdsl.core.MappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTypeInitiatorBuilder {

    private final MappingContext<?, ?> context;

    public <SRC_ROOT> UniTypeTerminatorBuilder<SRC_ROOT> from(Class<SRC_ROOT> sourceType) {
        return new UniTypeTerminatorBuilder<>(this.context, sourceType);
    }

}
