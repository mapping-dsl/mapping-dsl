package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiLeftSideExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRules mappingRules;

    public <SRC_TYPE> BiRightSideValueExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> leftSideExpression) {

        return new BiRightSideValueExpressionBuilder<>(
                this.mappingKey, new MappingRule<>(leftSideExpression), this.mappingRules);
    }

}
