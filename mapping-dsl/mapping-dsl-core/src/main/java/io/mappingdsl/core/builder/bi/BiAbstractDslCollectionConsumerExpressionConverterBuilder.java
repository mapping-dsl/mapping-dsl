package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.DslArrayExpression;
import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class BiAbstractDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> dslCollectionConsumerExpressionBuilder;

    BiAbstractDslCollectionConsumerExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context,
                                                              MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.dslCollectionConsumerExpressionBuilder =
                new BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    // delegate method
    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            DslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
    }

    // delegate method
    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            DslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
    }

    // delegate method
    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            AbstractDslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
    }

    // delegate method
    public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> from(
            AbstractDslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProducerFunction> targetExpression) {

        return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
    }

    public BiAbstractDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> usingHint(
            Class<? extends SRC_TYPE> hint) {

        return new BiAbstractDslCollectionConsumerExpressionBuilder<>(
                this.context, this.mappingRule.withInitialHint(hint));
    }

    public BiAbstractDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> usingGlobalHint() {
        return new BiAbstractDslCollectionConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<>(this.context, rule);
    }

    public static final class BiAbstractDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
        private final BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> dslCollectionConsumerExpressionBuilder;

        private BiAbstractDslCollectionConsumerExpressionBuilder(
                MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

            this.context = context;
            this.mappingRule = mappingRule;
            this.dslCollectionConsumerExpressionBuilder =
                    new BiTerminalCompatibleDslCollectionConsumerExpressionBuilder<>(this.context, this.mappingRule);
        }

        public BiTerminalIncompatibleDslCollectionConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> usingMapping() {
            return new BiTerminalIncompatibleDslCollectionConsumerExpressionBuilder<>(this.context, this.mappingRule);
        }

        // delegate method
        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
        }

        // delegate method
        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
        }

        // delegate method
        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
        }

        // delegate method
        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return this.dslCollectionConsumerExpressionBuilder.from(targetExpression);
        }

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

        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

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

        public <TRG_TYPE> BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return new BiConsumerMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
