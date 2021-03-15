package io.mappingdsl.core.builder.uni;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class UniDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            DslCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            DslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            AbstractDslCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            AbstractDslArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return new UniMappingConditionBuilder<>(
                this.context, this.mappingRule.withTerminalExpression(targetExpression));
    }

    public <TRG_TYPE> UniTerminalCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> initialExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withInitialExpressionConverter(
                initialExpressionConverter);

        return new UniTerminalCompatibleDslCollectionExpressionBuilder<>(this.context, rule);
    }

    public UniTerminalIncompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new UniTerminalIncompatibleDslCollectionExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniTerminalCompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniTerminalIncompatibleDslCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> UniDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> to(
                AbstractDslCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniDslExpressionHintsBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public <TRG_TYPE> UniDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> to(
                AbstractDslArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniDslExpressionHintsBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniDslExpressionHintsBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingHint(Class<? extends TRG_TYPE> hint) {
            return new UniMappingConditionBuilder<>(this.context, this.mappingRule.withTerminalHint(hint));
        }

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingGlobalHint() {
            return new UniMappingConditionBuilder<>(this.context, this.mappingRule);
        }

    }

}
