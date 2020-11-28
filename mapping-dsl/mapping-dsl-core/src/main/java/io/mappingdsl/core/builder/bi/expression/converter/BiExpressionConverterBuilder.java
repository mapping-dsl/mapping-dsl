package io.mappingdsl.core.builder.bi.expression.converter;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.bi.expression.condition.BiExpressionConditionBuilder;
import io.mappingdsl.core.builder.bi.expression.terminator.value.BiExpressionTerminatorBuilder;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;

public class BiExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> terminalExpressionBuilder;

    public BiExpressionConverterBuilder(
            MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new BiExpressionTerminatorBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> BiExpressionTerminatorBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverters(
            Converter<SRC_TYPE, NEW_SRC_TYPE> initialExpressionConverter,
            Converter<NEW_SRC_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiExpressionTerminatorBuilder<>(this.context, rule);
    }

    public <NEW_SRC_TYPE> BiExpressionTerminatorBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> usingConverter(
            BiConverter<SRC_TYPE, NEW_SRC_TYPE> converters) {

        Converter<SRC_TYPE, NEW_SRC_TYPE> forwardConverter = converters::convertForward;
        Converter<NEW_SRC_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(forwardConverter)
                .withTerminalExpressionConverter(backwardConverter);

        return new BiExpressionTerminatorBuilder<>(this.context, rule);
    }

    // delegate method
    public BiExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return this.terminalExpressionBuilder.with(terminalExpression);
    }

}
