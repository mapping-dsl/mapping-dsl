package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigurationBuilderBase {

    protected final ConfigurationBuilder configBuilder;
    protected final MappingContext<?, ?> context;

}
