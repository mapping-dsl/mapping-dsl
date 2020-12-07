package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;

import static io.mappingdsl.core.config.MissingMappingHandlingMode.RETURN_NULL;
import static io.mappingdsl.core.config.MissingMappingHandlingMode.TERMINATE;

public class MissingMappingHandlingConfigurationBuilder extends ConfigurationBuilderBase {

    public MissingMappingHandlingConfigurationBuilder(ConfigurationBuilder configBuilder, MappingContext<?, ?> context) {
        super(configBuilder, context);
    }

    public ConfigurationBuilder returnNull() {
        this.context.getConfiguration().setMissingMappingHandlingMode(RETURN_NULL);
        return this.configBuilder;
    }

    public ConfigurationBuilder terminate() {
        this.context.getConfiguration().setMissingMappingHandlingMode(TERMINATE);
        return this.configBuilder;
    }

}
