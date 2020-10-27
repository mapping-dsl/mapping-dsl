package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import lombok.experimental.Delegate;

public class UniConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final MappingRules mappingRules;

    @Delegate
    private final UniTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public UniConvertedExpressionBuilder(
            MappingKey<SRC_ROOT, TRG_ROOT> mappingKey, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule,
            MappingRules mappingRules) {

        this.mappingKey = mappingKey;
        this.mappingRule = mappingRule;
        this.mappingRules = mappingRules;

        this.terminalExpressionBuilder = new UniTerminalExpressionBuilder<>(
                this.mappingKey, this.mappingRule, this.mappingRules);
    }

    public <NEW_SRC_TYPE> UniTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            Converter<SRC_TYPE, NEW_SRC_TYPE> converter) {

        return new UniTerminalExpressionBuilder<>(
                this.mappingKey, this.mappingRule.withInitialExpressionConverter(converter), this.mappingRules);
    }

}
