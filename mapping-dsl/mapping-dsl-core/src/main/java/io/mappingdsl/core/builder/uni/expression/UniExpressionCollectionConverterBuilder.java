package io.mappingdsl.core.builder.uni.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.ValuesCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;

public class UniExpressionCollectionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniExpressionCollectionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> terminalExpressionBuilder;

    public UniExpressionCollectionConverterBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new UniExpressionCollectionTerminatorBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> UniExpressionCollectionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> converter) {

        return new UniExpressionCollectionTerminatorBuilder<>(
                this.context, this.mappingRule.withInitialExpressionConverter(converter));
    }

    // delegate method
    public UniExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            ValuesCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

}
