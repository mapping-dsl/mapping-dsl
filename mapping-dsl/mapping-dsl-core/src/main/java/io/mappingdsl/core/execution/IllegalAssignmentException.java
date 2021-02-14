package io.mappingdsl.core.execution;

import io.mappingdsl.core.expression.function.ValueConsumerFunction;

public class IllegalAssignmentException extends RuntimeException {

    public IllegalAssignmentException(ValueConsumerFunction consumerFunction, Class<?> valueType) {
        super(consumerFunction.toString() + " cannot consume value of type " + valueType.getCanonicalName() + ". "
                + "Probably mapping configuration or hint is missing for this pair of types.");
    }

}
