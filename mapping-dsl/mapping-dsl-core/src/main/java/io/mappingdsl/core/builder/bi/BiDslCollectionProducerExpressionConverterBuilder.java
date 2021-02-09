package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.DslArrayExpression;
import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            DslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new BiProducerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            DslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new BiProducerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public <TRG_TYPE> BiTerminalCompatibleDslCollectionProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withInitialExpressionConverter(converter);
        return new BiTerminalCompatibleDslCollectionProducerExpressionBuilder<>(this.context, rule);
    }

    public BiTerminalIncompatibleDslCollectionProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleDslCollectionProducerExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalCompatibleDslCollectionProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiProducerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiProducerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalIncompatibleDslCollectionProducerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiProducerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> BiProducerMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new BiProducerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
