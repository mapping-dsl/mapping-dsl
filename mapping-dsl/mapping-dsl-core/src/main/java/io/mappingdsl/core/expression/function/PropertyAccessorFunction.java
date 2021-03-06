package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

public class PropertyAccessorFunction implements PathProcessingFunction {

    private final ValueProducerFunction getMethodAccessorFunction;
    private final ValueConsumerFunction setMethodAccessorFunction;

    public PropertyAccessorFunction(ValueProducerFunction getMethodAccessorFunction,
                                    ValueConsumerFunction setMethodAccessorFunction) {

        this.getMethodAccessorFunction = getMethodAccessorFunction;
        this.setMethodAccessorFunction = setMethodAccessorFunction;
    }

    // delegate method
    @Override
    public Class<?> getConsumerType() {
        return this.setMethodAccessorFunction.getConsumerType();
    }

    @Override
    public boolean collectionConsumer() {
        return false;
    }

    // delegate method
    @Override
    public void consume(Object target, Object value) {
        this.setMethodAccessorFunction.consume(target, value);
    }

    // delegate method
    @Override
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
        return String.format("property [%s / %s]",
                this.getMethodAccessorFunction.toString(), this.setMethodAccessorFunction.toString());
    }

}
