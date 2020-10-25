package io.mappingdsl.core.builder;

public class UniFromTypeBuilder {

    public <SRC_ROOT> UniToTypeBuilder<SRC_ROOT> from(Class<SRC_ROOT> sourceType) {
        return new UniToTypeBuilder<>(sourceType);
    }

}
