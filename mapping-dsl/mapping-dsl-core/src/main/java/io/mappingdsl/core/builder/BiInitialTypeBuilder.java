package io.mappingdsl.core.builder;

public class BiInitialTypeBuilder {

    public <SRC_ROOT> BiTerminalTypeBuilder<SRC_ROOT> between(Class<SRC_ROOT> leftSideType) {
        return new BiTerminalTypeBuilder<>(leftSideType);
    }

}
