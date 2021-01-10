package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.CollectionSizeAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class DslCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, TYPE, FUN> {

    private final ValueExpression<ROOT, Integer, ValueProducerFunction> size =
            new ValueExpression<>(this, new CollectionSizeAccessorFunction());

    public DslCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public ValueExpression<ROOT, Integer, ValueProducerFunction> size() {
        return this.size;
    }

}
