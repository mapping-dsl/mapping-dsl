package io.mappingdsl.core.expression.function;

public class CollectionPropertyAccessorFunction implements ValueProcessingFunction {

    private final ValueProducerFunction getMethodAccessorFunction;
    private final ValueConsumerFunction setMethodAccessorFunction;

    public CollectionPropertyAccessorFunction(ValueProducerFunction getMethodAccessorFunction,
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
        return true;
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
    public String toString() {
        return String.format("collection property [%s / %s]",
                this.getMethodAccessorFunction.toString(), this.setMethodAccessorFunction.toString());
    }

}
