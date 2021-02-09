package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ArrayElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class DslArrayExpression<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends ArrayExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public DslArrayExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public DslExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new DslExpression<>(this, new ArrayElementAccessorFunction(index));
    }

}
