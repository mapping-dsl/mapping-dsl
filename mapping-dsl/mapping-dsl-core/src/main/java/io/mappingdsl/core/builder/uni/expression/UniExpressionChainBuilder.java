package io.mappingdsl.core.builder.uni.expression;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.MappingRule;
import io.mappingdsl.core.builder.uni.type.UniTypeInitiatorBuilder;
import io.mappingdsl.core.expression.DslHostExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.ValuesCollectionExpression;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class UniExpressionChainBuilder<SRC_ROOT, TRG_ROOT> {

    private final MappingContext<SRC_ROOT, TRG_ROOT> context;
    private final MappingRule<SRC_ROOT, TRG_ROOT> mappingRule;
    private final UniExpressionInitiatorBuilder<SRC_ROOT, TRG_ROOT> initialExpressionBuilder;

    public UniExpressionChainBuilder(MappingContext<SRC_ROOT, TRG_ROOT> context, MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.context = context;
        this.mappingRule = mappingRule;
        this.initialExpressionBuilder = new UniExpressionInitiatorBuilder<>(this.context);
    }

    public UniTypeInitiatorBuilder uniMapping() {
        registerCurrentRule();
        return new UniTypeInitiatorBuilder(this.context);
    }

    // delegate method
    public <SRC_TYPE> UniExpressionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValueExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> UniExpressionWrapperTerminatorBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            DslHostExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    // delegate method
    public <SRC_TYPE> UniExpressionCollectionConverterBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> produce(
            ValuesCollectionExpression<SRC_ROOT, SRC_TYPE, ? extends ValueProducerFunction> initialExpression) {

        registerCurrentRule();
        return this.initialExpressionBuilder.produce(initialExpression);
    }

    public MappingDsl build() {
        registerCurrentRule();
        return new MappingDsl(this.context.getConfiguration(), this.context.getMappingRules());
    }

    private void registerCurrentRule() {
        this.context.addMappingRule(mappingRule);
    }

}
