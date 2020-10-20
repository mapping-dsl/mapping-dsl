package io.mappingdsl.core;

import io.mappingdsl.core.execution.MappingExecutor;

public class MappingDsl {

    private final MappingRules mappingRules;

    public MappingDsl(MappingRules mappingRules) {
        this.mappingRules = mappingRules;
    }

    public <SRC, TRG> TRG map(SRC source, Class<TRG> targetType) {
        return new MappingExecutor(this.mappingRules).executeMapping(source, targetType);
    }

}
