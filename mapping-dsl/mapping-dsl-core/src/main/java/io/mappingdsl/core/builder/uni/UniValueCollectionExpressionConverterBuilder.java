package io.mappingdsl.core.builder.uni;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.array.AbstractRawArrayExpression;
import io.mappingdsl.core.expression.array.RawArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractRawCollectionExpression;
import io.mappingdsl.core.expression.collection.RawCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class UniValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> terminalExpressionBuilder;

    UniValueCollectionExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new UniTerminalValueCollectionExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> UniTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> converter) {

        return new UniTerminalValueCollectionExpressionBuilder<>(
                this.context, this.mappingRule.withInitialExpressionConverter(converter));
    }

    // delegate method
    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            RawCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    // delegate method
    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            RawArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    // delegate method
    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            AbstractRawCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    // delegate method
    public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
            AbstractRawArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression) {

        return this.terminalExpressionBuilder.to(targetExpression);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                RawCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                RawArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                AbstractRawCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                AbstractRawArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
