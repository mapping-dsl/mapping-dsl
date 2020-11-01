package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;

public class UniChainBuilder<SRC_ROOT, TRG_ROOT> extends UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;

    public UniChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        super(context);

        this.context = context;
        this.context.addMappingRule(mappingRule);
    }

    public MappingDsl build() {
        return new MappingDsl(this.context.getConfiguration(), this.context.getMappingRules());
    }

}