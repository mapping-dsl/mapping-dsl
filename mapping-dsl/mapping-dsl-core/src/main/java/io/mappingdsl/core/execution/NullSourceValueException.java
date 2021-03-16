package io.mappingdsl.core.execution;

import io.mappingdsl.core.expression.ExpressionBase;

public class NullSourceValueException extends RuntimeException {

    public NullSourceValueException(ExpressionBase<?, ?> expression) {
        super(expression == null
                ? "Source value is null"
                : String.format("Intermediate expression '%s' is evaluated to null", expression));
    }

}
