package io.mappingdsl.core.expression.function;

import org.apache.commons.collections4.iterators.SkippingIterator;

public final class CollectionElementAccessorFunction implements ValueProducerFunction {

    private final int index;

    public CollectionElementAccessorFunction(int index) {
        this.index = index;
    }

    @Override
    public Object produce(Object source) {
        if (source instanceof Iterable) {
            SkippingIterator<?> iterator = new SkippingIterator<>(((Iterable<?>) source).iterator(), this.index);
            return iterator.next();
        }

        throw new IllegalArgumentException(String.format(
                "Unable to element by index from the object %s", source.getClass().getCanonicalName()));
    }

}
