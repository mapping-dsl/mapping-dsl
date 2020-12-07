package io.mappingdsl.core.builder.bi.expression.terminator.value;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.bi.expression.condition.BiConsumerExpressionConditionBuilder;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiConsumerExpressionTerminatorBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiConsumerExpressionConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
            ValueExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerExpressionConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
