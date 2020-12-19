package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.builder.bi.type.BiTypeInitiatorBuilder;
import io.mappingdsl.core.builder.uni.type.UniInitialTypeBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigurationBuilder {

    private final MappingContext<?, ?> context;

    public <T> BeanFactoryConfigurationBuilder<T> onCreate(Class<T> type) {
        return new BeanFactoryConfigurationBuilder<>(this, this.context, type);
    }

    public NullHandlingConfigurationBuilder onNull() {
        return new NullHandlingConfigurationBuilder(this, this.context);
    }

    public MissingMappingHandlingConfigurationBuilder onMissingMapping() {
        return new MissingMappingHandlingConfigurationBuilder(this, this.context);
    }

    // duplicating MappingDslBuilder
    public UniInitialTypeBuilder uniMapping() {
        return new UniInitialTypeBuilder(this.context);
    }

    // duplicating MappingDslBuilder
    public BiTypeInitiatorBuilder biMapping() {
        return new BiTypeInitiatorBuilder(this.context);
    }

}
