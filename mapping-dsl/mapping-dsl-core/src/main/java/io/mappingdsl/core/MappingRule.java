package io.mappingdsl.core;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingRuleDirection mappingRuleDirection;
    private final ExpressionBase<SRC_ROOT, SRC_TYPE, ? extends ExpressionFunction> initialExpression;
    private final ExpressionBase<TRG_ROOT, TRG_TYPE, ? extends ExpressionFunction> terminalExpression;

    public MappingRule<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> withTerminalExpression(
            ExpressionBase<TRG_ROOT, TRG_TYPE, ? extends ExpressionFunction> terminalExpression) {

        return new MappingRule<>(this.mappingRuleDirection, this.initialExpression, terminalExpression);
    }

    public MappingRule<TRG_ROOT, TRG_TYPE, SRC_ROOT, SRC_TYPE> invert() {
        return new MappingRule<>(this.mappingRuleDirection, this.terminalExpression, this.initialExpression);
    }

    public enum MappingRuleDirection {
        FORWARD, BACKWARD, BOTH
    }

}
