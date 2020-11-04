package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;

public class UniConvertedExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniTerminalExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public UniConvertedExpressionBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new UniTerminalExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> UniTerminalExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            Converter<SRC_TYPE, NEW_SRC_TYPE> converter) {

        return new UniTerminalExpressionBuilder<>(
                this.context, this.mappingRule.withInitialExpressionConverter(converter));
    }

    // delegate method
    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> to(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

}
