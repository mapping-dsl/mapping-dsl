package io.mappingdsl.core.expression.function;

public interface ValueConsumerFunction extends ExpressionFunction {

    Class<?> getConsumerType();

    boolean collectionConsumer();

    void consume(Object target, Object value);

}
