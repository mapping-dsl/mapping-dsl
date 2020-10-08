package io.mappingdsl.core.expression.function;

public class RootIdentityFunction implements ValueProducerFunction {

    @Override
    public Object produce(Object source) {
        return source;
    }

    @Override
    public String toString() {
        return "RootIdentityFunction";
    }

}
