package io.mappingdsl.core;

import io.mappingdsl.core.builder.Condition;
import io.mappingdsl.core.builder.Converter;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MappingRule<SRC_ROOT, TRG_ROOT> {

    private final MappingRuleDirection mappingRuleDirection;
    private final ExpressionBase<SRC_ROOT, ?, ? extends ExpressionFunction> initialExpression;
    private final ExpressionBase<TRG_ROOT, ?, ? extends ExpressionFunction> terminalExpression;
    private final Converter<?, ?> initialExpressionConverter;
    private final Converter<?, ?> terminalExpressionConverter;
    private final Condition<?, ?> initialCondition;

    public MappingRule<SRC_ROOT, TRG_ROOT> withInitialExpressionConverter(
            Converter<?, ?> initialExpressionConverter) {

        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(this.terminalExpression)
                .initialExpressionConverter(initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
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
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withTerminalExpression(
            ExpressionBase<TRG_ROOT, ?, ? extends ExpressionFunction> terminalExpression) {

        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(this.initialCondition)
                .build();
    }

    public MappingRule<SRC_ROOT, TRG_ROOT> withInitialCondition(
            Condition<?, ?> initialCondition) {

        return MappingRule.<SRC_ROOT, TRG_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.initialExpression)
                .terminalExpression(terminalExpression)
                .initialExpressionConverter(this.initialExpressionConverter)
                .terminalExpressionConverter(this.terminalExpressionConverter)
                .initialCondition(initialCondition)
                .build();
    }

    public MappingRule<TRG_ROOT, SRC_ROOT> invert() {
        return MappingRule.<TRG_ROOT, SRC_ROOT>builder()
                .mappingRuleDirection(this.mappingRuleDirection)
                .initialExpression(this.terminalExpression)
                .terminalExpression(this.initialExpression)
                .initialExpressionConverter(this.terminalExpressionConverter)
                .terminalExpressionConverter(this.initialExpressionConverter)
                .initialCondition(this.initialCondition)
                .build();
    }

    public enum MappingRuleDirection {
        FORWARD, BACKWARD, BOTH
    }

}
