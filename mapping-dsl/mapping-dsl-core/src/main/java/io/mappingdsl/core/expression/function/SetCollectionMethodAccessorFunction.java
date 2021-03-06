package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;
import ice.bricks.streams.StreamUtils;

import java.util.Collection;
import java.util.stream.Stream;

public class SetCollectionMethodAccessorFunction implements ValueConsumerFunction {

    private final Class<?> collectionType;
    private final Class<?> elementType;
    private final String name;

    public SetCollectionMethodAccessorFunction(Class<?> collectionType, Class<?> elementType, String name) {
        this.collectionType = collectionType;
        this.elementType = elementType;
        this.name = name;
    }

    @Override
    public Class<?> getConsumerType() {
        return this.elementType;
    }

    @Override
    public boolean collectionConsumer() {
        return true;
    }

    @Override
    public void consume(Object target, Object value) {
        Collection<?> values = StreamUtils.collectStream((Stream<?>) value, this.collectionType);
        ReflectionUtils.invokeMethod(target, this.name, new Class<?> [] { this.collectionType }, new Object [] { values });
    }

    @Override
    public String toString() {
        return String.format("collection setter %s consuming %s with elements of type %s",
                this.name, this.collectionType.getCanonicalName(), this.elementType.getCanonicalName());
    }

}
