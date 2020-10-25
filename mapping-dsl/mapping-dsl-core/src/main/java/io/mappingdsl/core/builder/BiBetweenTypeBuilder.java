package io.mappingdsl.core.builder;

public class BiBetweenTypeBuilder {

    public <SRC_ROOT> BiAndTypeBuilder<SRC_ROOT> between(Class<SRC_ROOT> leftSideType) {
        return new BiAndTypeBuilder<>(leftSideType);
    }

}
