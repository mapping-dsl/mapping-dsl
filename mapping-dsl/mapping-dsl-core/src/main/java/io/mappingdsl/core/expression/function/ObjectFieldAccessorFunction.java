package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

public class ObjectFieldAccessorFunction implements PathProcessingFunction {

    private final String name;
    private final Class<?> type;

    public ObjectFieldAccessorFunction(Class<?> type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Object produce(Object source) {
        return ReflectionUtils.readField(source, this.name);
    }

    @Override
    public Class<?> getConsumerType() {
        return this.type;
    }

    @Override
    public Object getConsumer(Object target) {
        return ReflectionUtils.readField(target, this.name);
    }

    @Override
    public void consume(Object target, Object value) {
        ReflectionUtils.writeField(target, this.name, value);
    }

    @Override
    public Object step(Object target) {
        Object property = produce(target);

        if (property == null) {
            property = ReflectionUtils.generateNewInstance(this.type);
            ReflectionUtils.writeField(target, this.name, property);
        }
        return property;
    }

    @Override
    public String toString() {
        return String.format("%s.%s (field access)", this.type.getSimpleName(), this.name);
    }

}
