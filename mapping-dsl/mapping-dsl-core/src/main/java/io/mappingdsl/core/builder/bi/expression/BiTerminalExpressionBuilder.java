package io.mappingdsl.core.builder.bi.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return new BiMappingConditionBuilder<>(this.context, this.mappingRule.withTerminalExpression(terminalExpression));
    }

}
