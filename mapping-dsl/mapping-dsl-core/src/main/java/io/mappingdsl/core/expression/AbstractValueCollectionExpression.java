package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;

public class AbstractValueCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, TYPE, FUN> {

    public AbstractValueCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
