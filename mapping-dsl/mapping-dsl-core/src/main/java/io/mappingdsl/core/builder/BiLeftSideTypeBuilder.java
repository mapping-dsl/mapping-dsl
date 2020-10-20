package io.mappingdsl.core.builder;

public class BiLeftSideTypeBuilder {

    public <SRC_ROOT> BiRightSideTypeBuilder<SRC_ROOT> between(Class<SRC_ROOT> leftSideType) {
        return new BiRightSideTypeBuilder<>(leftSideType);
    }

}
