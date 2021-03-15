package io.mappingdsl.core.expression.collection;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.AbstractDslExpression;

public class AbstractDslCollectionExpression<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public AbstractDslCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractDslExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new AbstractDslExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
