package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.builder.bi.BiInitialTypeBuilder;
import io.mappingdsl.core.builder.config.ConfigurationBuilder;
import io.mappingdsl.core.builder.uni.UniInitialTypeBuilder;
import io.mappingdsl.core.config.MappingConfiguration;

public final class MappingDslBuilder {

    private final MappingContext<?, ?> context = new MappingContext<>()
            .withConfiguration(new MappingConfiguration())
            .withMappingRules(new MappingRules());

    public ConfigurationBuilder configuration() {
        return new ConfigurationBuilder(this.context);
    }

    public UniInitialTypeBuilder uniMapping() {
        return new UniInitialTypeBuilder(this.context);
    }

    public BiInitialTypeBuilder biMapping() {
        return new BiInitialTypeBuilder(this.context);
    }

}
