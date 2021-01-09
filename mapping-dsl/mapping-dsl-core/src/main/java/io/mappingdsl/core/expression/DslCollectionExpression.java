package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.GetMethodAccessorFunction;

public class DslCollectionExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, TYPE, FUN> {

    private final ValueExpression<ROOT, Integer, GetMethodAccessorFunction> size =
            new ValueExpression<>(this, new GetMethodAccessorFunction(Integer.class, "size"));

    public DslCollectionExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public ValueExpression<ROOT, Integer, GetMethodAccessorFunction> size() {
        return this.size;
    }

}
