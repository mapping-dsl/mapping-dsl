package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.MappingRules;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniSupplyBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;
    private final MappingRules mappingRules;

    public <SRC_TYPE> UniCompositeBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> sourceExpression) {

        MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> mappingRule = new MappingRule<>(
                MappingRuleDirection.FORWARD, sourceExpression, null);

        return new UniCompositeBuilder<>(this.mappingKey, mappingRule, this.mappingRules);
    }

}
