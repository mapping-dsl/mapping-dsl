package io.mappingdsl.core.builder.bi;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.expression.AbstractDslExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BiAbstractDslConsumerExpressionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;

    public BiTerminalAbstractDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> usingHint(
            Class<? extends SRC_TYPE> hint) {

        return new BiTerminalAbstractDslConsumerExpressionBuilder<>(
                this.context, this.mappingRule.withInitialHint(hint));
    }

    public BiTerminalAbstractDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, SRC_TYPE> usingGlobalHint() {
        return new BiTerminalAbstractDslConsumerExpressionBuilder<>(this.context, this.mappingRule);
    }

    public <TRG_TYPE> BiTerminalCompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> usingConverter(
            Converter<TRG_TYPE, SRC_TYPE> converter) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule.withTerminalExpressionConverter(converter);
        return new BiTerminalCompatibleDslConsumerExpressionBuilder<>(this.context, rule);
    }

    public static final class BiTerminalAbstractDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> {

        private final MappingContext<SRC_ROOT, TRG_ROOT> context;
        private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
        private final BiTerminalCompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> compatibleDslConsumerExpressionBuilder;

        private BiTerminalAbstractDslConsumerExpressionBuilder(
                MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {

            this.context = context;
            this.mappingRule = mappingRule;
            this.compatibleDslConsumerExpressionBuilder = new BiTerminalCompatibleDslConsumerExpressionBuilder<>(this.context, this.mappingRule);
        }

        public BiTerminalIncompatibleDslConsumerExpressionBuilder<SRC_ROOT, TRG_ROOT> usingMapping() {
            return new BiTerminalIncompatibleDslConsumerExpressionBuilder<>(this.context, this.mappingRule);
        }

        // delegate method
        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                DslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return this.compatibleDslConsumerExpressionBuilder.from(targetExpression);
        }

        // delegate method
        public BiConsumerMappingConditionBuilder<SRC_ROOT, TRG_ROOT, TRG_TYPE> from(
                AbstractDslExpression<TRG_ROOT, TRG_TYPE, ? extends ValueProducerFunction> targetExpression) {

            return this.compatibleDslConsumerExpressionBuilder.from(targetExpression);
        }

    }

}
