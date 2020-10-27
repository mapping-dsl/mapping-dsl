package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import lombok.experimental.Delegate;

public class BiConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final MappingRules mappingRules;

    @Delegate
    private final BiTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public BiConvertedExpressionBuilder(
            MappingKey<SRC_ROOT, TRG_ROOT> mappingKey, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule,
            MappingRules mappingRules) {

        this.mappingKey = mappingKey;
        this.mappingRule = mappingRule;
        this.mappingRules = mappingRules;

        this.terminalExpressionBuilder = new BiTerminalExpressionBuilder<>(
                this.mappingKey, this.mappingRule, this.mappingRules);
    }

    public <NEW_SRC_TYPE> BiTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverters(
            Converter<SRC_TYPE, NEW_SRC_TYPE> initialExpressionConverter,
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalExpressionBuilder<>(this.mappingKey, rule, this.mappingRules);
    }

    public <NEW_SRC_TYPE> BiTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            BiConverter<SRC_TYPE, NEW_SRC_TYPE> converters) {

        Converter<SRC_TYPE, NEW_SRC_TYPE> forwardConverter = converters::convertForward;
        Converter<NEW_SRC_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(forwardConverter)
                .withTerminalExpressionConverter(backwardConverter);

        return new BiTerminalExpressionBuilder<>(this.mappingKey, rule, this.mappingRules);
    }

}
