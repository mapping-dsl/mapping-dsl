package io.mappingdsl.core.builder.bi.expression.terminator.wrapper;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.bi.expression.condition.BiExpressionConditionBuilder;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiConsumerWrapperExpressionTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalCompatibleWrapperConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> asIs() {
        return new BiTerminalCompatibleWrapperConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <NEW_SRC_TYPE> BiTerminalCompatibleWrapperConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, NEW_SRC_TYPE> usingConverter(
            Converter<NEW_SRC_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleWrapperConsumerExpressionBuilder<>(this.context, rule);
    }

    public BiTerminalIncompatibleWrapperConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleWrapperConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor
    public static class BiTerminalCompatibleWrapperConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> from(
                DslHost<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiExpressionConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor
    public static class BiTerminalIncompatibleWrapperConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> BiExpressionConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> from(
                DslHost<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiExpressionConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
