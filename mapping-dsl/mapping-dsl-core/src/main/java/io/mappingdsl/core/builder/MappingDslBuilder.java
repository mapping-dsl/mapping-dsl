package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingConfiguration;
import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.builder.bi.type.BiInitialTypeBuilder;
import io.mappingdsl.core.builder.config.ConfigurationBuilder;
import io.mappingdsl.core.builder.uni.type.UniTypeInitiatorBuilder;

public class MappingDslBuilder {

    private final MappingContext<?, ?> context = new MappingContext<>()
            .withConfiguration(new MappingConfiguration())
            .withMappingRules(new MappingRules());

    public ConfigurationBuilder configuration() {
        return new ConfigurationBuilder(this, this.context);
    }

    public UniTypeInitiatorBuilder uniMapping() {
        return new UniTypeInitiatorBuilder(this.context);
    }

    public BiInitialTypeBuilder biMapping() {
        return new BiInitialTypeBuilder(this.context);
    }

}
