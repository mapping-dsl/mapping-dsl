package io.mappingdsl.core.expression.function;

public final class ArrayLengthAccessorFunction implements ValueProducerFunction {

    @Override
    public Object produce(Object source) {
        if (source.getClass().isArray()) {
            return ((Object[]) source).length;
        }

        throw new IllegalArgumentException(String.format(
                "Unable to get size of the object %s", source.getClass().getCanonicalName()));
    }

}
