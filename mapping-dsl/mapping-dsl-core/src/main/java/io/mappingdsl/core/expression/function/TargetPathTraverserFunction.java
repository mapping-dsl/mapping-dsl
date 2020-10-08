package io.mappingdsl.core.expression.function;

public interface TargetPathTraverserFunction extends ExpressionFunction {

    Object step(Object target);

}
