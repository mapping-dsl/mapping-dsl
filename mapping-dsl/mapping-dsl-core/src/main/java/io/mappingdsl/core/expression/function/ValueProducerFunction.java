package io.mappingdsl.core.expression.function;

public interface ValueProducerFunction extends ExpressionFunction {

    Object produce(Object source);

}
