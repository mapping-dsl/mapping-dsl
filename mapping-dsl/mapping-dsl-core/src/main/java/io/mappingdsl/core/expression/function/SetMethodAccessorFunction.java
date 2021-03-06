package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

public class SetMethodAccessorFunction implements ValueConsumerFunction {

    private final String name;
    private final Class<?> type;

    public SetMethodAccessorFunction(Class<?> type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Class<?> getConsumerType() {
        return this.type;
    }

    @Override
    public boolean collectionConsumer() {
        return false;
    }

    @Override
    public void consume(Object target, Object value) {
        ReflectionUtils.invokeMethod(target, this.name, new Class<?> [] { this.type }, new Object [] { value });
    }

    @Override
    public String toString() {
        return String.format("setter %s consuming %s", this.name, this.type.getCanonicalName());
    }

}
