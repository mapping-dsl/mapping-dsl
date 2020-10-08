package io.mappingdsl.core.expression.function;

public interface ValueConsumerFunction extends ExpressionFunction {

    void consume(Object target, Object value);

}
