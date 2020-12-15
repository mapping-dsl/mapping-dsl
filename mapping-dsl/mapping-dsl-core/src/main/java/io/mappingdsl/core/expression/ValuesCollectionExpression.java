package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;

public class ValuesCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, TYPE, FUN> {

    public ValuesCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
