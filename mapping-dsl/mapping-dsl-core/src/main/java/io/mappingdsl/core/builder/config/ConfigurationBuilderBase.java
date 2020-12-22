package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class ConfigurationBuilderBase {

    protected final ConfigurationBuilder configBuilder;
    protected final MappingContext<?, ?> context;

}
