package io.mappingdsl.core.builder.bi.expression.converter;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.bi.expression.BiExpressionConditionBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.value.BiProducerExpressionTerminatorBuilder;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class BiProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiProducerExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public BiProducerExpressionConverterBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new BiProducerExpressionTerminatorBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> BiProducerExpressionTerminatorBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(
                terminalExpressionConverter);

        return new BiProducerExpressionTerminatorBuilder<>(this.context, rule);
    }

    // delegate method
    public BiExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> from(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return this.terminalExpressionBuilder.from(targetExpression);
    }

}
