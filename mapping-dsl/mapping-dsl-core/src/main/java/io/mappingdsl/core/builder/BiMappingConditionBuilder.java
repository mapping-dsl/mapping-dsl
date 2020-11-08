package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class BiMappingConditionBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, TRG_TYPE> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final BiChainBuilder<SRC_ROOT, TRG_ROOT> chainBuilder;

    public BiMappingConditionBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.chainBuilder = new BiChainBuilder<>(this.context, this.mappingRule);
    }

    public BiChainBuilder<SRC_ROOT, TRG_ROOT> when(
            Condition<SRC_TYPE, TRG_TYPE> forwardCondition, Condition<TRG_TYPE, SRC_TYPE> backwardCondition) {

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialCondition(forwardCondition).withTerminalCondition(backwardCondition);

        return new BiChainBuilder<>(this.context, rule);
    }

    public BiChainBuilder<SRC_ROOT, TRG_ROOT> when(BiCondition<SRC_TYPE, TRG_TYPE> condition) {
        Condition<SRC_TYPE, TRG_TYPE> forwardCondition = condition::testForward;
        Condition<TRG_TYPE, SRC_TYPE> backwardCondition = condition::testBackward;

        MappingRule<SRC_ROOT, TRG_ROOT> rule = this.mappingRule
                .withInitialCondition(forwardCondition).withTerminalCondition(backwardCondition);

        return new BiChainBuilder<>(this.context, rule);
    }

    public <NEW_SRC_TYPE> BiConvertedExpressionBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> bind(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProcessingFunction> initialExpression) {

        return this.chainBuilder.bind(initialExpression);
    }

    public <NEW_SRC_TYPE> BiConvertedExpressionConsumerBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> supply(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        return this.chainBuilder.supply(initialExpression);
    }

    public <NEW_SRC_TYPE> BiConvertedExpressionProducerBuilder<SRC_ROOT, NEW_SRC_TYPE, TRG_ROOT> consume(
            ValueExpression<SRC_ROOT, NEW_SRC_TYPE, ? extends ValueConsumerFunction> initialExpression) {

        return this.chainBuilder.consume(initialExpression);
    }

    public MappingDsl build() {
        return this.chainBuilder.build();
    }

}