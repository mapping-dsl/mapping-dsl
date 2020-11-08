package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.builder.MappingDslBuilder;

import static io.mappingdsl.core.MappingConfiguration.MissingMappingHandlingMode.RETURN_NULL;
import static io.mappingdsl.core.MappingConfiguration.MissingMappingHandlingMode.TERMINATE;

public class MissingMappingHandlingConfigurationBuilder extends ConfigurationBuilderBase {

    public MissingMappingHandlingConfigurationBuilder(
            MappingDslBuilder mappingDslBuilder, MappingContext<?, ?> context) {

        super(mappingDslBuilder, context);
    }

    public MappingDslBuilder returnNull() {
        this.context.getConfiguration().setMissingMappingHandlingMode(RETURN_NULL);
        return this.mappingDslBuilder;
    }

    public MappingDslBuilder terminate() {
        this.context.getConfiguration().setMissingMappingHandlingMode(TERMINATE);
        return this.mappingDslBuilder;
    }

}
