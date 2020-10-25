package io.mappingdsl.core.builder;

public class MappingDslBuilder {

    public UniFromTypeBuilder uniMapping() {
        return new UniFromTypeBuilder();
    }

    public BiBetweenTypeBuilder biMapping() {
        return new BiBetweenTypeBuilder();
    }

}
