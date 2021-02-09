package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.CollectionElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class DslCollectionExpression<ROOT, COLLECTION_TYPE, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, COLLECTION_TYPE, ELEMENT_TYPE, FUN> {

    public DslCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public DslExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new DslExpression<>(this, new CollectionElementAccessorFunction(index));
    }

}
