package io.mappingdsl.core.builder;

public class MappingDslBuilder {

    public UniInitialTypeBuilder uniMapping() {
        return new UniInitialTypeBuilder();
    }

    public BiInitialTypeBuilder biMapping() {
        return new BiInitialTypeBuilder();
    }

}
