package io.mappingdsl.core.builder;

public class MappingDslBuilder {

    public UniSourceTypeBuilder uniMapping() {
        return new UniSourceTypeBuilder();
    }

    public BiLeftSideTypeBuilder biMapping() {
        return new BiLeftSideTypeBuilder();
    }

}
