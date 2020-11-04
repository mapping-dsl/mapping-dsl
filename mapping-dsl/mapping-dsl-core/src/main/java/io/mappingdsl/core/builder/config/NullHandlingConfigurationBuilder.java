package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.builder.MappingDslBuilder;
import lombok.RequiredArgsConstructor;

import static io.mappingdsl.core.MappingConfiguration.NullHandlingMode.PROCEED;
import static io.mappingdsl.core.MappingConfiguration.NullHandlingMode.TERMINATE;

@RequiredArgsConstructor
public class NullHandlingConfigurationBuilder {

    private final MappingDslBuilder mappingDslBuilder;
    private final MappingContext<?, ?> context;

    public MappingDslBuilder proceed() {
        this.context.getConfiguration().setNullHandlingMode(PROCEED);
        return this.mappingDslBuilder;
    }

    public MappingDslBuilder terminate() {
        this.context.getConfiguration().setNullHandlingMode(TERMINATE);
        return this.mappingDslBuilder;
    }

}
