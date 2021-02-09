package io.mappingdsl.core.expression.function;

import java.util.Collection;
import java.util.stream.StreamSupport;

public final class CollectionSizeAccessorFunction implements ValueProducerFunction {

    @Override
    public Object produce(Object source) {
        if (source instanceof Collection) {
            return ((Collection<?>) source).size();
        }
        else if (source instanceof Iterable) {
            long count = StreamSupport.stream(((Iterable<?>) source).spliterator(), false).count();
            // returns Integer.MAX_VALUE if more elements exist according to the spec for Collection#size()
            return (int) Math.min(Integer.MAX_VALUE, count);
        }

        throw new IllegalArgumentException(String.format(
                "Unable to get size of the object %s", source.getClass().getCanonicalName()));
    }

}
