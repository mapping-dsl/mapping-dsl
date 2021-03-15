package io.mappingdsl.core.expression.collection;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.CollectionSizeAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.RawExpression;

public abstract class CollectionExpressionBase<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    private final RawExpression<ROOT, Integer, ValueProducerFunction> size =
            new RawExpression<>(this, new CollectionSizeAccessorFunction());

    public CollectionExpressionBase(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public RawExpression<ROOT, Integer, ValueProducerFunction> size() {
        return this.size;
    }

}
