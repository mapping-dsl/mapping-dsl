package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniSourceExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;

    public <SRC_TYPE> UniTargetValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> sourceExpression) {

        return new UniTargetValueExpressionBuilder<>(this.mappingKey, new UniMappingRule<>(sourceExpression));
    }

}
