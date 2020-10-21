package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTargetValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, ?> mappingRule;
    private final MappingRules mappingRules;

    public UniExpressionsChainBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> to(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> target) {

        return new UniExpressionsChainBuilder<>(
                this.mappingKey, this.mappingRule.withTerminalExpression(target), this.mappingRules);
    }

}
