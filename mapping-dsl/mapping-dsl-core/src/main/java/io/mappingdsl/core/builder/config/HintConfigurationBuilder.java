package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;

public final class HintConfigurationBuilder<T> extends ConfigurationBuilderBase {

    private final Class<T> type;

    HintConfigurationBuilder(ConfigurationBuilder configBuilder, MappingContext<?, ?> context, Class<T> type) {
        super(configBuilder, context);
        this.type = type;
    }

    public ConfigurationBuilder useHint(Class<? extends T> hint) {
        this.context.getConfiguration().getHintConfiguration().registerHint(this.type, hint);
        return this.configBuilder;
    }

}
