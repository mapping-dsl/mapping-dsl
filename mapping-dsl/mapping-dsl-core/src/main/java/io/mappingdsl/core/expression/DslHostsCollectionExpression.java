package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;

public class DslHostsCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, TYPE, FUN> {

    public DslHostsCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
