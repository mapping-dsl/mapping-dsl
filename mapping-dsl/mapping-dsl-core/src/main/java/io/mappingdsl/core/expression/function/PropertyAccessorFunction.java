package io.mappingdsl.core.expression.function;

public class PropertyAccessorFunction implements ValueProcessingFunction {

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
    public Object getConsumer(Object target) {
        return this.setMethodAccessorFunction.getConsumer(target);
    }

    // delegate method
    public void consume(Object target, Object value) {
        this.setMethodAccessorFunction.consume(target, value);
    }

    // delegate method
    public Object produce(Object source) {
        return this.getMethodAccessorFunction.produce(source);
    }

}
