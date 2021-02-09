package io.mappingdsl.core.expression.function;

public final class ArrayElementAccessorFunction implements ValueProducerFunction {

    private final int index;

    public ArrayElementAccessorFunction(int index) {
        this.index = index;
    }

    @Override
    public Object produce(Object source) {
        if (source.getClass().isArray()) {
            return ((Object[]) source)[this.index];
        }

        throw new IllegalArgumentException(String.format(
                "Unable to element by index from the object %s", source.getClass().getCanonicalName()));
    }

}
