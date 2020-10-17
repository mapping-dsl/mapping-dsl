package io.mappingdsl.core.builder;

import io.mappingdsl.core.MapperDsl;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRules;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniExpressionsChainBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final UniMappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> mappingRule;

    public MapperDsl build() {
        MappingRules mappingRules = new MappingRules();
        mappingRules.addMappingRule(this.mappingKey, this.mappingRule);

        return new MapperDsl(mappingRules);
    }

}
