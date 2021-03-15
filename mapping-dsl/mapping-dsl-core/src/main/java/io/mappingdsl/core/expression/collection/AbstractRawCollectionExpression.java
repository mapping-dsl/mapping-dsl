package io.mappingdsl.core.expression.collection;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractRawExpression;

public class AbstractRawCollectionExpression<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public AbstractRawCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractRawExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new AbstractRawExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
