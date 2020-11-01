package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;
import org.jetbrains.annotations.NotNull;

public abstract class DslHost<ROOT, TYPE, FUN extends ExpressionFunction> extends ExpressionBase<ROOT, TYPE, FUN> {

    public DslHost(@NotNull FUN expressionFunction) {
        super(expressionFunction);
    }

    public DslHost(@NotNull ExpressionBase<ROOT, ?, ?> parentExpression, @NotNull FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
