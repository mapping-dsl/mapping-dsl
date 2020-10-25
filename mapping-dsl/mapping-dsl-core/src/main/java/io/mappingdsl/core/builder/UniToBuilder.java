package io.mappingdsl.core.builder;

import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;

public interface UniToBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT> {

    UniChainBuilder<SRC_ROOT, SRC_TYPE, TRG_ROOT, SRC_TYPE> to(
            ValueExpression<TRG_ROOT, SRC_TYPE, ? extends ValueConsumerFunction> targetExpression);

}
