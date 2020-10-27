package io.mappingdsl.core.builder;

public class UniInitialTypeBuilder {

    public <SRC_ROOT> UniTerminalTypeBuilder<SRC_ROOT> from(Class<SRC_ROOT> sourceType) {
        return new UniTerminalTypeBuilder<>(sourceType);
    }

}
