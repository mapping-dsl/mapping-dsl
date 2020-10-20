package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiRightSideValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, ?> mappingRule;

    public BiExpressionsChainBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> rightSideExpression) {

        return new BiExpressionsChainBuilder<>(
                this.mappingKey, this.mappingRule.withTerminalExpression(rightSideExpression));
    }

}
