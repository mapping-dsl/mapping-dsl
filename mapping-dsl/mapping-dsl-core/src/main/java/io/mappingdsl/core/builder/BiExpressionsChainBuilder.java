package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiExpressionsChainBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> mappingRule;

    public MappingDsl build() {
        MappingRules mappingRules = new MappingRules();
        mappingRules.addMappingRule(this.mappingKey, this.mappingRule);
        mappingRules.addMappingRule(this.mappingKey.invert(), this.mappingRule.invert());

        return new MappingDsl(mappingRules);
    }

}
