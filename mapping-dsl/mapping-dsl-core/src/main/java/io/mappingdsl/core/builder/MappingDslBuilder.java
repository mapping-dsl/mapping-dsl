package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.builder.bi.type.BiTypeInitiatorBuilder;
import io.mappingdsl.core.builder.config.ConfigurationBuilder;
import io.mappingdsl.core.builder.uni.type.UniTypeInitiatorBuilder;
import io.mappingdsl.core.config.MappingConfiguration;

public class MappingDslBuilder {

    private final MappingContext<?, ?> context = new MappingContext<>()
            .withConfiguration(new MappingConfiguration())
            .withMappingRules(new MappingRules());

    public ConfigurationBuilder configuration() {
        return new ConfigurationBuilder(this.context);
    }

    public UniTypeInitiatorBuilder uniMapping() {
        return new UniTypeInitiatorBuilder(this.context);
    }

    public BiTypeInitiatorBuilder biMapping() {
        return new BiTypeInitiatorBuilder(this.context);
    }

}
