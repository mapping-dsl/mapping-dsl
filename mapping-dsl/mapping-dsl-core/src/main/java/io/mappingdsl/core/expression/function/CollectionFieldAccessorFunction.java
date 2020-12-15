package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

import java.util.Collection;

public class CollectionFieldAccessorFunction implements ValueProcessingFunction {

    private final Class<?> collectionType;
    private final Class<?> elementType;
    private final String name;

    public CollectionFieldAccessorFunction(Class<?> collectionType, Class<?> elementType, String name) {
        this.collectionType = collectionType;
        this.elementType = elementType;
        this.name = name;
    }

    @Override
    public Object produce(Object source) {
        return ReflectionUtils.readField(source, this.name);
    }

    @Override
    public Class<?> getConsumerType() {
        return this.elementType;
    }

    @Override
    public void consume(Object target, Object value) {
        Collection<Object> collection = (Collection<Object>) produce(target);

        if (collection == null) {
            collection = ReflectionUtils.generateNewCollectionInstance(this.collectionType);
            ReflectionUtils.writeField(target, this.name, collection);
        }

        collection.add(value);
    }

    @Override
    public String toString() {
        return String.format("%s (collection field access)", this.name);
    }

}
