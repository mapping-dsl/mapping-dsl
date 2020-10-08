package io.mappingdsl.core.expression;

import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

public class DslHost<ROOT, TYPE> extends ExpressionBase<ROOT, TYPE, ValueProducerFunction> {

    public DslHost() {
        super(new RootIdentityFunction());
    }

}
