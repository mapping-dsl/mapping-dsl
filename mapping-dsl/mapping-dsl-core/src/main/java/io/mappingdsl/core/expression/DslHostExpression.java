package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;
import org.jetbrains.annotations.NotNull;

public abstract class DslHostExpression<ROOT, TYPE, FUN extends ExpressionFunction>
        extends ExpressionBase<ROOT, TYPE, FUN> {

    public DslHostExpression(@NotNull FUN expressionFunction) {
        super(expressionFunction);
    }

    public DslHostExpression(@NotNull ExpressionBase<ROOT, ?, ?> parentExpression, @NotNull FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

}
