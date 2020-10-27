package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import lombok.experimental.Delegate;

public class BiConvertedExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final MappingRules mappingRules;

    @Delegate
    private final BiTerminalExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public BiConvertedExpressionProducerBuilder(
            MappingKey<SRC_ROOT, TRG_ROOT> mappingKey, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule,
            MappingRules mappingRules) {

        this.mappingKey = mappingKey;
        this.mappingRule = mappingRule;
        this.mappingRules = mappingRules;

        this.terminalExpressionBuilder = new BiTerminalExpressionProducerBuilder<>(
                this.mappingKey, this.mappingRule, this.mappingRules);
    }

    public <NEW_SRC_TYPE> BiTerminalExpressionProducerBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalExpressionProducerBuilder<>(this.mappingKey, rule, this.mappingRules);
    }

}
