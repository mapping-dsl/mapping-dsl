package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.builder.MappingDslBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigurationBuilder {

    private final MappingDslBuilder mappingDslBuilder;
    private final MappingContext<?, ?> context;

    public NullHandlingConfigurationBuilder onNull() {
        return new NullHandlingConfigurationBuilder(this.mappingDslBuilder, this.context);
    }

}
