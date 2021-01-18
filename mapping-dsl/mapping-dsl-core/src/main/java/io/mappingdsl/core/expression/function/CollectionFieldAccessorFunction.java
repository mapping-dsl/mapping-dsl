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
    public boolean collectionConsumer() {
        return true;
    }

    @Override
    public void consume(Object target, Object value) {
        Collection<Object> collection = ReflectionUtils.readField(target, this.name);

        if (collection == null) {
            collection = ReflectionUtils.generateNewCollectionInstance(this.collectionType);
            ReflectionUtils.writeField(target, this.name, collection);
        }

        collection.addAll((Collection<?>) value);
    }

    @Override
    public String toString() {
        return String.format("collection %s of type %s with elements of type %s",
                this.name, this.collectionType.getCanonicalName(), this.elementType.getCanonicalName());
    }

}
