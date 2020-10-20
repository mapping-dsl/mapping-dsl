package io.mappingdsl.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MappingRules {

    private final Map<MappingKey<?, ?>, Set<MappingRule<?, ?, ?, ?>>> rules = new HashMap<>();

    public <SRC, TRG> void addMappingRule(MappingKey<SRC, TRG> mappingKey, MappingRule<SRC, ?, TRG, ?> context) {
        this.rules.computeIfAbsent(mappingKey, key -> new HashSet<>()).add(context);
    }

    public Set<MappingRule<?, ?, ?, ?>> getMappingRules(MappingKey<?, ?> mappingKey) {
        return this.rules.get(mappingKey);
    }

}
