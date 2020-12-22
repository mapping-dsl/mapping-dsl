package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.execution.MappingBeanFactory;

public final class BeanFactoryConfigurationBuilder<T> extends ConfigurationBuilderBase {

    private final Class<T> type;

    public BeanFactoryConfigurationBuilder(
            ConfigurationBuilder configBuilder, MappingContext<?, ?> context, Class<T> type) {

        super(configBuilder, context);
        this.type = type;
    }

    public ConfigurationBuilder useFactory(MappingBeanFactory<T> factory) {
        this.context.getConfiguration().getBeanFactoryConfiguration().registerFactory(this.type, factory);
        return this.configBuilder;
    }

}
