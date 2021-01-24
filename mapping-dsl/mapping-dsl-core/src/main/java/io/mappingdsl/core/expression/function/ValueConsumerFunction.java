package io.mappingdsl.core.expression.function;

public interface ValueConsumerFunction extends ExpressionFunction {

    Class<?> getConsumerType();

    boolean collectionConsumer();

    default boolean canConsume(Class<?> type) {
        return getConsumerType().isAssignableFrom(type);
    }

    void consume(Object target, Object value);

}
