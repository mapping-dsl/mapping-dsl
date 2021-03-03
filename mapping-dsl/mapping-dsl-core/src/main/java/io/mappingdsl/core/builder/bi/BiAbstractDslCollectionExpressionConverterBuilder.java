package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class BiAbstractDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiAbstractCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> dslCollectionExpressionBuilder;

    BiAbstractDslCollectionExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context,
                                                      MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

        this.context = context;
        this.mappingRule = mappingRule;
        this.dslCollectionExpressionBuilder =
                new BiAbstractCompatibleDslCollectionExpressionBuilder<>(this.context, this.mappingRule);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            DslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

        return this.dslCollectionExpressionBuilder.with(targetExpression);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            DslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

        return this.dslCollectionExpressionBuilder.with(targetExpression);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            AbstractDslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

        return this.dslCollectionExpressionBuilder.with(targetExpression);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            AbstractDslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

        return this.dslCollectionExpressionBuilder.with(targetExpression);
    }

    public BiAbstractDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingHint(
            Class<? extends SRC_TYPE> hint) {

        return new BiAbstractDslCollectionExpressionBuilder<>(this.context, this.mappingRule.withInitialHint(hint));
    }

    public BiAbstractDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingGlobalHint() {
        return new BiAbstractDslCollectionExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiAbstractCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverters(
            Converter<SRC_TYPE, TRG_TYPE> initialExpressionConverter,
            Converter<TRG_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiAbstractCompatibleDslCollectionExpressionBuilder<>(this.context, rule);
    }

    public <TRG_TYPE> BiAbstractCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            BiConverter<SRC_TYPE, TRG_TYPE> converters) {

        Converter<SRC_TYPE, TRG_TYPE> forwardConverter = converters::convertForward;
        Converter<TRG_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        return usingConverters(forwardConverter, backwardConverter);
    }

    public static final class BiAbstractDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
        private final BiAbstractCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> dslCollectionExpressionBuilder;

        private BiAbstractDslCollectionExpressionBuilder(
                MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

            this.context = context;
            this.mappingRule = mappingRule;
            this.dslCollectionExpressionBuilder =
                    new BiAbstractCompatibleDslCollectionExpressionBuilder<>(this.context, this.mappingRule);
        }

        public BiTerminalIncompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
            return new BiTerminalIncompatibleDslCollectionExpressionBuilder<>(this.context, this.mappingRule);
        }

        // delegate method
        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
                DslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return this.dslCollectionExpressionBuilder.with(targetExpression);
        }

        // delegate method
        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
                DslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return this.dslCollectionExpressionBuilder.with(targetExpression);
        }

        // delegate method
        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
                AbstractDslCollectionExpression<TRG_ROOT, ?, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return this.dslCollectionExpressionBuilder.with(targetExpression);
        }

        // delegate method
        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
                AbstractDslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return this.dslCollectionExpressionBuilder.with(targetExpression);
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiAbstractCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                AbstractDslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                AbstractDslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalIncompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> BiDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                AbstractDslCollectionExpression<TRG_ROOT, ?, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiDslExpressionHintsBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> BiDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                AbstractDslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> targetExpression) {

            return new BiDslExpressionHintsBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingHint(Class<? extends TRG_TYPE> hint) {
            return new BiMappingConditionBuilder<>(this.context, this.mappingRule.withTerminalHint(hint));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingGlobalHint() {
            return new BiMappingConditionBuilder<>(this.context, this.mappingRule);
        }

    }

}
