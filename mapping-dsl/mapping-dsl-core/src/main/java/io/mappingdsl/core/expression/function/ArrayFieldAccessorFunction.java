package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayFieldAccessorFunction implements ValueProcessingFunction {

    private final Class<?> elementType;
    private final String name;

    public ArrayFieldAccessorFunction(Class<?> elementType, String name) {
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
        Object targetArray = ReflectionUtils.readField(target, this.name);
        Object[] sourceArray = ((Stream<?>) value).toArray();
        int sourceLength = sourceArray.length;

        int targetLength = 0;
        if (targetArray == null) {
            targetArray = Array.newInstance(this.elementType, sourceLength);
        }
        else {
            targetLength = Array.getLength(targetArray);
            targetArray = Arrays.copyOf((Object[]) targetArray, targetLength + sourceLength);
        }

        System.arraycopy(sourceArray, 0, targetArray, targetLength, sourceLength);
        ReflectionUtils.writeField(target, this.name, targetArray);
    }

    @Override
    public String toString() {
        return String.format("array %s with elements of type %s", this.name, this.elementType.getCanonicalName());
    }

}
