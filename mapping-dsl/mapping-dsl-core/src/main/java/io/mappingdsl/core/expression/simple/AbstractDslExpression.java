package io.mappingdsl.core.expression.simple;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import org.jetbrains.annotations.NotNull;

public class AbstractDslExpression<ROOT, TYPE, FUN extends ExpressionFunction> extends ExpressionBase<ROOT, TYPE, FUN> {

    public AbstractDslExpression(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    public AbstractDslExpression(@NotNull FUN expressionFunction) {
        super(expressionFunction);
    }

}
