package io.mappingdsl.core.expression.collection;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractValueExpression;

public class AbstractValueCollectionExpression<ROOT, COLLECTION_TYPE, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, COLLECTION_TYPE, ELEMENT_TYPE, FUN> {

    public AbstractValueCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractValueExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new AbstractValueExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
