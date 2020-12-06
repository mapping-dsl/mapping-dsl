package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

public class PropertyAccessorFunction implements PathProcessingFunction {

    private final GetMethodAccessorFunction getMethodAccessorFunction;
    private final SetMethodAccessorFunction setMethodAccessorFunction;

    public PropertyAccessorFunction(GetMethodAccessorFunction getMethodAccessorFunction,
                                    SetMethodAccessorFunction setMethodAccessorFunction) {

        this.getMethodAccessorFunction = getMethodAccessorFunction;
        this.setMethodAccessorFunction = setMethodAccessorFunction;
    }

    // delegate method
    public Class<?> getConsumerType() {
        return this.setMethodAccessorFunction.getConsumerType();
    }

    // delegate method
    public void consume(Object target, Object value) {
        this.setMethodAccessorFunction.consume(target, value);
    }

    // delegate method
    public Object produce(Object source) {
        return this.getMethodAccessorFunction.produce(source);
    }

    @Override
    public Object step(Object target) {
        Object property = produce(target);

        if (property == null) {
            property = ReflectionUtils.generateNewInstance(getConsumerType());
            consume(target, property);
        }

        return property;
    }

    @Override
    public String toString() {
        return String.format("[%s / %s] (property access)",
                this.getMethodAccessorFunction.toString(), this.setMethodAccessorFunction.toString());
    }

}
