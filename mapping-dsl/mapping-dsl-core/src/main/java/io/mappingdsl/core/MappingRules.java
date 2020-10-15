package io.mappingdsl.core;

import io.mappingdsl.core.builder.UniMappingContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MappingRules {

    private final Map<MappingKey<?, ?>, Set<UniMappingContext<?, ?, ?, ?>>> rules = new HashMap<>();

    public <SRC, TRG> void addMappingRule(MappingKey<SRC, TRG> mappingKey, UniMappingContext<SRC, ?, TRG, ?> context) {
        this.rules.computeIfAbsent(mappingKey, key -> new HashSet<>()).add(context);
    }

    public Set<UniMappingContext<?, ?, ?, ?>> getMappingRules(MappingKey<?, ?> mappingKey) {
        return this.rules.get(mappingKey);
    }

}
