package io.mappingdsl.core.expression.function;

public interface ValueConsumerFunction extends ExpressionFunction {

    Class<?> getConsumerType();

    Object getConsumer(Object target);

    void consume(Object target, Object value);

}
