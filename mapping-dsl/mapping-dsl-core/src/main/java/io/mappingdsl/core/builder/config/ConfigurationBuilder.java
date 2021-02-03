package io.mappingdsl.core.builder.config;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.builder.bi.BiInitialTypeBuilder;
import io.mappingdsl.core.builder.uni.UniInitialTypeBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConfigurationBuilder {

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

    public <T> HintConfigurationBuilder<T> onAbstract(Class<T> type) {
        return new HintConfigurationBuilder<>(this, this.context, type);
    }

    // duplicating MappingDslBuilder
    public UniInitialTypeBuilder uniMapping() {
        return new UniInitialTypeBuilder(this.context);
    }

    // duplicating MappingDslBuilder
    public BiInitialTypeBuilder biMapping() {
        return new BiInitialTypeBuilder(this.context);
    }

}
