package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.ExpressionFunction;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ExpressionBase<ROOT, FUN extends ExpressionFunction> {

    @Getter
    private final ExpressionBase<ROOT, ?> parentExpression;

    @Getter
    private final FUN expressionFunction;

    public ExpressionBase(@NotNull FUN expressionFunction) {
        this.parentExpression = null;
        this.expressionFunction = expressionFunction;
    }

    public ExpressionBase(@NotNull ExpressionBase<ROOT, ?> parentExpression, @NotNull FUN expressionFunction) {
        this.parentExpression = parentExpression;
        this.expressionFunction = expressionFunction;
    }

    @Override
    public String toString() {
        Deque<ExpressionBase<ROOT, ?>> expressionChain = new ArrayDeque<>();

        ExpressionBase<ROOT, ?> expression = this;
        while (expression != null) {
            expressionChain.addFirst(expression);
            expression = expression.getParentExpression();
        }

        return expressionChain.stream()
                .map(ExpressionBase::getExpressionFunction)
                .map(Objects::toString)
                .collect(Collectors.joining(" -> "));
    }

}
