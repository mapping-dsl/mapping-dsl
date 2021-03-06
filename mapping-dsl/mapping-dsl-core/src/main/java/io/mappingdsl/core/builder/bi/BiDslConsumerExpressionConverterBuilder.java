package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiDslConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            DslExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            AbstractDslExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public <TRG_TYPE> BiTerminalCompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleDslConsumerExpressionBuilder<>(this.context, rule);
    }

    public BiTerminalIncompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleDslConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

}
