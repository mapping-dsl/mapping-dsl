package io.mappingdsl.core.expression.collection;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.ValueExpression;

public class ValueCollectionExpression<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public ValueCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public ValueExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new ValueExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
