package io.mappingdsl.core.expression.simple;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;

public class RawExpression<ROOT, TYPE, FUN extends ExpressionFunction> extends ExpressionBase<ROOT, FUN> {

    public RawExpression(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
