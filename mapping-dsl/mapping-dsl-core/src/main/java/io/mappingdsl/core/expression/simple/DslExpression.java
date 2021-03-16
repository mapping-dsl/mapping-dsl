package io.mappingdsl.core.expression.simple;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import org.jetbrains.annotations.NotNull;

public class DslExpression<ROOT, TYPE, FUN extends ExpressionFunction> extends ExpressionBase<ROOT, FUN> {

    public DslExpression(@NotNull FUN expressionFunction) {
        super(expressionFunction);
    }

    public DslExpression(@NotNull ExpressionBase<ROOT, ?> parentExpression, @NotNull FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
