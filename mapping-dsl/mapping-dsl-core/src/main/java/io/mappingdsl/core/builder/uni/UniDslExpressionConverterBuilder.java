package io.mappingdsl.core.builder.uni;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class UniDslExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public UniTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> asIs() {
        return new UniTerminalCompatibleDslExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> UniTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<SRC_TYPE, TRG_TYPE> initialExpressionConverter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withInitialExpressionConverter(
                initialExpressionConverter);

        return new UniTerminalCompatibleDslExpressionBuilder<>(this.context, rule);
    }

    public UniTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> usingMapping() {
        return new UniTerminalIncompatibleDslExpressionBuilder<>(this.context, this.mappingRule);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniTerminalCompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UniTerminalIncompatibleDslExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

        public <TRG_TYPE> UniMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> to(
                DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueConsumerFunction> targetExpression) {

            return new UniMappingConditionBuilder<>(
                    this.context, this.mappingRule.withTerminalExpression(targetExpression));
        }

    }

}
