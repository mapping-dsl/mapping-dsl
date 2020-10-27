package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final MappingRules mappingRules;

    public BiChainBuilder<SRC_ROOT, TRG_ROOT> with(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return new BiChainBuilder<>(
                this.mappingKey, this.mappingRule.withTerminalExpression(terminalExpression), this.mappingRules);
    }

}
