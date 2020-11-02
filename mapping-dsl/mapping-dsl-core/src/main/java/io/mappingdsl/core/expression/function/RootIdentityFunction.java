package io.mappingdsl.core.expression.function;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RootIdentityFunction implements ValueProducerFunction {

    private final String holderName;

    @Override
    public Object produce(Object source) {
        return source;
    }

    @Override
    public String toString() {
        return this.holderName;
    }

}
