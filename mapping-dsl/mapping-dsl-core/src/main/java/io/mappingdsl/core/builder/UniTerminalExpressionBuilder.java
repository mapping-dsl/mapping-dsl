package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public UniChainBuilder<SRC_ROOT, TRG_ROOT> to(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniChainBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
