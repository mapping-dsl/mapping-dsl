package io.mappingdsl.core.expression.array;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ArrayElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import io.mappingdsl.core.expression.simple.ValueExpression;

public class ValueArrayExpression<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends ArrayExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public ValueArrayExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public ValueExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new ValueExpression<>(this, new ArrayElementAccessorFunction(index));
    }

}
