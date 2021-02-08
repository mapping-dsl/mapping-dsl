package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class ValueCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, TYPE, FUN> {

    public ValueCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public ValueExpression<ROOT, TYPE, ValueProducerFunction> get(int index) {
        return new ValueExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
