package io.mappingdsl.core;

import io.mappingdsl.core.common.Condition;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MappingRule<SRC_ROOT, TRG_ROOT> {

    private final MappingRuleDirection mappingRuleDirection;
    private final ExpressionBase<SRC_ROOT, ? extends ExpressionFunction> initialExpression;
    private final ExpressionBase<TRG_ROOT, ? extends ExpressionFunction> terminalExpression;
    private final Converter<?, ?> initialExpressionConverter;
    private final Converter<?, ?> terminalExpressionConverter;
    private final Condition<?> initialCondition;
    private final Condition<?> terminalCondition;
    private final Class<?> initialHint;
    private final Class<?> terminalHint;

    public MappingRule<SRC_ROOT, TRG_ROOT> withInitialExpressionConverter(
            Converter<?, ?> initialExpressionConverter) {

        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(this.terminalExpression)
                .initialExpressionConverter(initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .terminalCondition(this.terminalCondition)
                .initialHint(this.initialHint)
                .terminalHint(this.terminalHint)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withTerminalExpressionConverter(
            Converter<?, ?> terminalExpressionConverter) {

        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(this.terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .terminalCondition(this.terminalCondition)
                .initialHint(this.initialHint)
                .terminalHint(this.terminalHint)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withTerminalExpression(
            ExpressionBase<TRG_ROOT, ? extends ExpressionFunction> terminalExpression) {

        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .terminalCondition(this.terminalCondition)
                .initialHint(this.initialHint)
                .terminalHint(this.terminalHint)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withInitialCondition(Condition<?> initialCondition) {
        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(initialCondition)
                .terminalCondition(this.terminalCondition)
                .initialHint(this.initialHint)
                .terminalHint(this.terminalHint)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withTerminalCondition(Condition<?> terminalCondition) {
        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(this.terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .terminalCondition(terminalCondition)
                .initialHint(this.initialHint)
                .terminalHint(this.terminalHint)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withInitialHint(Class<?> initialHint) {
        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(this.terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .terminalCondition(this.terminalCondition)
                .initialHint(initialHint)
                .terminalHint(this.terminalHint)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withTerminalHint(Class<?> terminalHint) {
        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(this.terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .terminalCondition(this.terminalCondition)
                .initialHint(this.initialHint)
                .terminalHint(terminalHint)
                .build();
    }

    public MappingRule<TRG_ROOT, SRC_ROOT> invert() {
        return MappingRule.<TRG_ROOT, SRC_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.terminalExpression)
                .terminalExpression(this.initialExpression)
                .initialExpressionConverter(this.terminalExpressionConverter)
                .terminalExpressionConverter(this.initialExpressionConverter)
                .initialCondition(this.terminalCondition)
                .terminalCondition(this.initialCondition)
                .initialHint(this.terminalHint)
                .terminalHint(this.initialHint)
                .build();
    }

    public enum MappingRuleDirection {
        FORWARD, BACKWARD, BOTH
    }

}
