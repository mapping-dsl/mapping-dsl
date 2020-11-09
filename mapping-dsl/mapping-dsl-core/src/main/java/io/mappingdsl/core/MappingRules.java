package io.mappingdsl.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MappingRules {

    private final Map<MappingKey<?, ?>, Set<MappingRule<?, ?>>> rules = new HashMap<>();

    public <SRC_ROOT, TRG_ROOT> void addMappingRule(
            MappingKey<SRC_ROOT, TRG_ROOT> mappingKey, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        MappingRule.MappingRuleDirection ruleDirection = mappingRule.getMappingRuleDirection();

        switch (ruleDirection) {
            case FORWARD:
                this.rules.computeIfAbsent(mappingKey, key -> new HashSet<>()).add(mappingRule);
                break;

            case BACKWARD:
                this.rules.computeIfAbsent(mappingKey.invert(), key -> new HashSet<>()).add(mappingRule.invert());
                break;

            case BOTH:
                this.rules.computeIfAbsent(mappingKey, key -> new HashSet<>()).add(mappingRule);
                this.rules.computeIfAbsent(mappingKey.invert(), key -> new HashSet<>()).add(mappingRule.invert());
                break;

            default:
                throw new IllegalArgumentException("Unsupported mapping direction: " + ruleDirection.name());
        }
    }

    public Set<MappingRule<?, ?>> getMappingRules(MappingKey<?, ?> mappingKey) {
        return this.rules.get(mappingKey);
    }

    public boolean containsMappingRules(MappingKey<?, ?> mappingKey) {
        return this.rules.containsKey(mappingKey);
    }

}
