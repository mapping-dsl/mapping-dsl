package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.builder.MappingDslBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigurationBuilderBase {

    protected final MappingDslBuilder mappingDslBuilder;
    protected final MappingContext<?, ?> context;

}
