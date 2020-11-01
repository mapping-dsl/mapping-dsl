package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;

public class UniChainBuilder<SRC_ROOT, TRG_ROOT> extends UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingRules mappingRules;

    public UniChainBuilder(
            MappingKey<SRC_ROOT, TRG_ROOT> mappingKey, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule,
            MappingRules mappingRules) {

        super(mappingKey, mappingRules);

        this.mappingRules = mappingRules;
        this.mappingRules.addMappingRule(mappingKey, mappingRule);
    }

    public MappingDsl build() {
        return new MappingDsl(this.mappingRules);
    }

}
