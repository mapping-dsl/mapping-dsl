package io.mappingdsl.core.expression.function;

public interface ValueConsumerFunction extends ExpressionFunction {

    Class<?> getConsumerType();

    void consume(Object target, Object value);

}
