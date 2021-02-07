package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;

public class AbstractDslCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends CollectionExpressionBase<ROOT, TYPE, FUN> {

    public AbstractDslCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
