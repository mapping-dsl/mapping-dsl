package io.mappingdsl.core;

import io.mappingdsl.core.config.MappingConfiguration;
import io.mappingdsl.core.execution.MappingExecutor;

public class MappingDsl {

    private final MappingConfiguration mappingConfiguration;
    private final MappingRules mappingRules;

    public MappingDsl(MappingConfiguration mappingConfiguration, MappingRules mappingRules) {
        this.mappingConfiguration = mappingConfiguration;
        this.mappingRules = mappingRules;
    }

    public <SRC, TRG> TRG map(SRC source, Class<TRG> targetType) {
        return new MappingExecutor(this.mappingConfiguration, this.mappingRules).executeMapping(source, targetType);
    }

}
