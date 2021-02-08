package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class AbstractDslCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, TYPE, FUN> {

    public AbstractDslCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractDslExpression<ROOT, TYPE, ValueProducerFunction> get(int index) {
        return new AbstractDslExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
