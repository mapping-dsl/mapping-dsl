package io.mappingdsl.core.builder;

public class UniSourceTypeBuilder {

    public <SRC_ROOT> UniTargetTypeBuilder<SRC_ROOT> from(Class<SRC_ROOT> sourceType) {
        return new UniTargetTypeBuilder<>(sourceType);
    }

}
