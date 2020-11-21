package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

public class GetMethodAccessorFunction implements ValueProducerFunction {

    private final String name;
    private final Class<?> type;

    public GetMethodAccessorFunction(Class<?> type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Object produce(Object source) {
        return ReflectionUtils.invokeMethod(source, this.name);
    }

    @Override
    public String toString() {
        return String.format("%s.%s (getter access)", this.type.getSimpleName(), this.name);
    }

}
