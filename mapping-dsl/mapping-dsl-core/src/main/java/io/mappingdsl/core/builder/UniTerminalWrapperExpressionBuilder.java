package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTerminalWrapperExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public <TRG_TYPE> UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> to(
            DslHost<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

}
