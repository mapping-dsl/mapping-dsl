package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.array.AbstractRawArrayExpression;
import io.mappingdsl.core.expression.array.RawArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractRawCollectionExpression;
import io.mappingdsl.core.expression.collection.RawCollectionExpression;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public final class BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> terminalExpressionBuilder;

    BiValueCollectionExpressionConverterBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.terminalExpressionBuilder = new BiTerminalValueCollectionExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverters(
            Converter<SRC_TYPE, TRG_TYPE> initialExpressionConverter,
            Converter<TRG_TYPE, SRC_TYPE> terminalExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(initialExpressionConverter)
                .withTerminalExpressionConverter(terminalExpressionConverter);

        return new BiTerminalValueCollectionExpressionBuilder<>(this.context, rule);
    }

    public <TRG_TYPE> BiTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            BiConverter<SRC_TYPE, TRG_TYPE> converters) {

        Converter<SRC_TYPE, TRG_TYPE> forwardConverter = converters::convertForward;
        Converter<TRG_TYPE, SRC_TYPE> backwardConverter = converters::convertBackward;

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialExpressionConverter(forwardConverter)
                .withTerminalExpressionConverter(backwardConverter);

        return new BiTerminalValueCollectionExpressionBuilder<>(this.context, rule);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            RawCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return this.terminalExpressionBuilder.with(terminalExpression);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            RawArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return this.terminalExpressionBuilder.with(terminalExpression);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            AbstractRawCollectionExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return this.terminalExpressionBuilder.with(terminalExpression);
    }

    // delegate method
    public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> with(
            AbstractRawArrayExpression<TRG_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

        return this.terminalExpressionBuilder.with(terminalExpression);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class BiTerminalValueCollectionExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                RawCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(terminalExpression));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                RawArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(terminalExpression));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                AbstractRawCollectionExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(terminalExpression));
        }

        public BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> with(
                AbstractRawArrayExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProcessingFunction> terminalExpression) {

            return new BiMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(terminalExpression));
        }

    }

}
