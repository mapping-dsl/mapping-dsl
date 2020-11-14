package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiTerminalIncompatibleWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public <TRG_TYPE> BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
            DslHost<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new BiMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
