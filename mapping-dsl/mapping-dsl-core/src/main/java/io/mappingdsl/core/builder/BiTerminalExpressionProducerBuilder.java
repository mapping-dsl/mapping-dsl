package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalExpressionProducerBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiChainBuilder<SRC_ROOT, TRG_ROOT> from(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiChainBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
