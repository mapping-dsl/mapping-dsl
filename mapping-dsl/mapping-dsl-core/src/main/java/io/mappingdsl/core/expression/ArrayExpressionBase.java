package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ArrayLengthAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class ArrayExpressionBase<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public final ValueExpression<ROOT, Integer, ValueProducerFunction> length =
            new ValueExpression<>(this, new ArrayLengthAccessorFunction());

    public ArrayExpressionBase(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
