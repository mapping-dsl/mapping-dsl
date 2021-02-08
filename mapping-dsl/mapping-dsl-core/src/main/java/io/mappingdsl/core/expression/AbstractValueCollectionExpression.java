package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class AbstractValueCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, TYPE, FUN> {

    public AbstractValueCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractValueExpression<ROOT, TYPE, ValueProducerFunction> get(int index) {
        return new AbstractValueExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
