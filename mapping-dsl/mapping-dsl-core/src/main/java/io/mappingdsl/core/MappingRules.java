package io.mappingdsl.core;

import io.mappingdsl.core.builder.UniMappingRule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MappingRules {

    private final Map<MappingKey<?, ?>, Set<UniMappingRule<?, ?, ?, ?>>> rules = new HashMap<>();

    public <SRC, TRG> void addMappingRule(MappingKey<SRC, TRG> mappingKey, UniMappingRule<SRC, ?, TRG, ?> context) {
        this.rules.computeIfAbsent(mappingKey, key -> new HashSet<>()).add(context);
    }

    public Set<UniMappingRule<?, ?, ?, ?>> getMappingRules(MappingKey<?, ?> mappingKey) {
        return this.rules.get(mappingKey);
    }

}
