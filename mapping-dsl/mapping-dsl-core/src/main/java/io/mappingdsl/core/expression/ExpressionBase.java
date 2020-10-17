package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ExpressionBase<ROOT, TYPE, FUN extends ExpressionFunction> {

    @Nullable
    @Getter
    private final ExpressionBase<ROOT, ?, ?> parentExpression;

    @NotNull
    @Getter
    private final FUN expressionFunction;

    public ExpressionBase(@NotNull FUN expressionFunction) {
        this.parentExpression = null;
        this.expressionFunction = expressionFunction;
    }

    public ExpressionBase(@Nullable ExpressionBase<ROOT, ?, ?> parentExpression, @NotNull FUN expressionFunction) {
        this.parentExpression = parentExpression;
        this.expressionFunction = expressionFunction;
    }

    @Override
    public String toString() {
        StringBuilder expressionPath = new StringBuilder(this.expressionFunction.toString());

        ExpressionBase<ROOT, ?, ?> expression = parentExpression;
        while (expression != null) {
            expressionPath.append(" > ").append(expression.getExpressionFunction().toString());
            expression = expression.getParentExpression();
        }

        return expressionPath.toString();
    }

}