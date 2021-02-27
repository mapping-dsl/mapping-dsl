package io.mappingdsl.core.expression.function;

import org.apache.commons.lang3.ClassUtils;

public interface ValueConsumerFunction extends ExpressionFunction {

    Class<?> getConsumerType();

    boolean collectionConsumer();

    default boolean canConsume(Class<?> type) {
        Class<?> consumerType = getConsumerType();

        if (consumerType.isPrimitive()) {
            return ClassUtils.primitiveToWrapper(consumerType).isAssignableFrom(type);
        }

        return consumerType.isAssignableFrom(type);
    }

    void consume(Object target, Object value);

}
