package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiValueExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> mappingRule;
    private final MappingRules mappingRules;

    public BiExpressionsChainBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> from(
            ValueExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiExpressionsChainBuilder<>(
                this.mappingKey, this.mappingRule.withTerminalExpression(targetExpression), this.mappingRules);
    }

}
