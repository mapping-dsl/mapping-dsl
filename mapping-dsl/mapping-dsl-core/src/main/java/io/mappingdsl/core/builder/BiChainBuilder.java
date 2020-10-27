package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;

public class BiChainBuilder<SRC_ROOT, TRG_ROOT> extends BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final MappingRules mappingRules;

    public BiChainBuilder(
            MappingKey<SRC_ROOT, TRG_ROOT> mappingKey, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule,
            MappingRules mappingRules) {

        super(mappingKey, mappingRules);

        this.mappingKey = mappingKey;
        this.mappingRule = mappingRule;
        this.mappingRules = mappingRules;

        this.mappingRules.addMappingRule(this.mappingKey, this.mappingRule);
    }

    public MappingDsl build() {
        return new MappingDsl(this.mappingRules);
    }

}
