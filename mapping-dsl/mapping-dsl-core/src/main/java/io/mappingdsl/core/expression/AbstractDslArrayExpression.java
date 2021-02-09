package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ArrayElementAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class AbstractDslArrayExpression<ROOT, ELEMENT_TYPE, FUN extends ExpressionFunction>
        extends ArrayExpressionBase<ROOT, ELEMENT_TYPE, FUN> {

    public AbstractDslArrayExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractDslExpression<ROOT, ELEMENT_TYPE, ValueProducerFunction> get(int index) {
        return new AbstractDslExpression<>(this, new ArrayElementAccessorFunction(index));
    }

}
