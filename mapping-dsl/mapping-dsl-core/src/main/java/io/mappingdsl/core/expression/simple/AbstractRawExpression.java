package io.mappingdsl.core.expression.simple;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;

public class AbstractRawExpression<ROOT, TYPE, FUN extends ExpressionFunction> extends ExpressionBase<ROOT, TYPE, FUN> {

    public AbstractRawExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
