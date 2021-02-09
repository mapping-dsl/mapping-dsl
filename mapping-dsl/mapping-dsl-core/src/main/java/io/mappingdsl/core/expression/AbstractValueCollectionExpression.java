package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class AbstractValueCollectionExpression<ROOT, COLLECTION_TYPE, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, COLLECTION_TYPE, ELEMENT_TYPE, FUN> {

    public AbstractValueCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractValueExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new AbstractValueExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
