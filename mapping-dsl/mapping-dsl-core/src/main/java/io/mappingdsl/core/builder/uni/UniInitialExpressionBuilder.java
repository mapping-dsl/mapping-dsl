package io.mappingdsl.core.builder.uni;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.MappingRule.MappingRuleDirection;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.AbstractValueArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.array.ValueArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.AbstractValueCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.collection.ValueCollectionExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.AbstractValueExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import io.mappingdsl.core.expression.simple.ValueExpression;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;

    public <SRC_TYPE> UniValueExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniValueExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniValueExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniValueExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractValueCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniValueCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractValueArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniValueCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniDslExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniDslExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniDslExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractDslExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniDslExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractDslCollectionExpression<SRC_ROOT, ?, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

    public <SRC_TYPE> UniDslCollectionExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            AbstractDslArrayExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        MappingRule<SRC_ROOT, TRG_ROOT> mappingRule = MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(MappingRuleDirection.FORWARD)
                .initialExpression(initialExpression)
                .build();

        return new UniDslCollectionExpressionConverterBuilder<>(this.context, mappingRule);
    }

}
