package io.mappingdsl.core.builder.uni.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValuesCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniExpressionCollectionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public UniExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            ValuesCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniExpressionConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
