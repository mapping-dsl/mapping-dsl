package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            DslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            DslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return new BiConsumerMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public <TRG_TYPE> BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<>(this.context, rule);
    }

    public BiTerminalIncompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> usingMapping() {
        return new BiTerminalIncompatibleDslCollectionConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalIncompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
