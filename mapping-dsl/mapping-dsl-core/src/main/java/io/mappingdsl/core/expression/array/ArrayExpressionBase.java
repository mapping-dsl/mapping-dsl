package io.mappingdsl.core.expression.array;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ArrayLengthAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.RawExpression;

public abstract class ArrayExpressionBase<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public final RawExpression<ROOT, Integer, ValueProducerFunction> length =
            new RawExpression<>(this, new ArrayLengthAccessorFunction());

    public ArrayExpressionBase(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
