package io.mappingdsl.core.builder;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UniMappingContext<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final ExpressionBase<SRC_ROOT, SRC_TYPE, ? extends ExpressionFunction> sourceExpression;

    private ExpressionBase<TRG_ROOT, TRG_TYPE, ? extends ExpressionFunction> targetExpression;

    public UniMappingContext(ExpressionBase<SRC_ROOT, SRC_TYPE, ? extends ExpressionFunction> sourceExpression,
                             ExpressionBase<TRG_ROOT, TRG_TYPE, ? extends ExpressionFunction> targetExpression) {
        this.sourceExpression = sourceExpression;
        this.targetExpression = targetExpression;
    }

    public <NEW_TRG_TYPE> UniMappingContext<SRC_ROOT, SRC_TYPE, TRG_ROOT, NEW_TRG_TYPE> withTarget(
            ExpressionBase<TRG_ROOT, NEW_TRG_TYPE, ? extends ExpressionFunction> targetExpression) {

        return new UniMappingContext<>(this.sourceExpression, targetExpression);
    }

}
