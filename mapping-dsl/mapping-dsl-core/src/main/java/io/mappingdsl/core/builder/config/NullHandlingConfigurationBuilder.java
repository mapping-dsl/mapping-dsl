package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;

import static io.mappingdsl.core.config.NullHandlingMode.PROCEED;
import static io.mappingdsl.core.config.NullHandlingMode.TERMINATE;

public final class NullHandlingConfigurationBuilder extends ConfigurationBuilderBase {

    NullHandlingConfigurationBuilder(ConfigurationBuilder configBuilder, MappingContext<?, ?> context) {
        super(configBuilder, context);
    }

    public ConfigurationBuilder proceed() {
        this.context.getConfiguration().setNullHandlingMode(PROCEED);
        return this.configBuilder;
    }

    public ConfigurationBuilder terminate() {
        this.context.getConfiguration().setNullHandlingMode(TERMINATE);
        return this.configBuilder;
    }

}
