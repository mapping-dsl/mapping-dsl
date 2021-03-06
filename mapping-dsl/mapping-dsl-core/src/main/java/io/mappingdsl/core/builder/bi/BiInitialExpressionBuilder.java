package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.AbstractRawArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.array.RawArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.AbstractRawCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.collection.RawCollectionExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.AbstractRawExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import io.mappingdsl.core.expression.simple.RawExpression;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;

    public <SRC_TYPE> BiValueExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            RawExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiValueExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            AbstractRawExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiValueExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            RawCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            RawArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            AbstractRawCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            AbstractRawArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiDslExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiAbstractDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            AbstractDslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiAbstractDslExpressionBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiAbstractDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            AbstractDslCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiAbstractDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiAbstractDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> bind(
            AbstractDslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BOTH)
                .initialExpression(initialExpression)
                .build();

        return new BiAbstractDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            RawExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractRawExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            RawCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            RawArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractRawCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractRawArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractDslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractDslCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionProducerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractDslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionProducerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            RawExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            AbstractRawExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            RawCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            RawArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            AbstractRawCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiValueCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            AbstractRawArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiValueCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiAbstractDslConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            AbstractDslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiAbstractDslConsumerExpressionBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiDslCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiAbstractDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            AbstractDslCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiAbstractDslCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> BiAbstractDslCollectionConsumerExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> consume(
            AbstractDslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.BACKWARD)
                .initialExpression(initialExpression)
                .build();

        return new BiAbstractDslCollectionConsumerExpressionConverterBuilder<>(this.context, mappingRule);
    }

}
