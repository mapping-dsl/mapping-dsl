package io.mappingdsl.core.expression.function;

import ice.bricks.reflection.ReflectionUtils;

import java.lang.reflect.Array;
import java.util.stream.Stream;

public class SetArrayMethodAccessorFunction implements ValueConsumerFunction {

    private final Class<?> arrayType;
    private final Class<?> elementType;
    private final String name;

    public SetArrayMethodAccessorFunction(Class<?> arrayType, Class<?> elementType, String name) {
        this.arrayType = arrayType;
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
        Object[] sourceArray = ((Stream<?>) value).toArray();
        Object targetArray = Array.newInstance(this.elementType, sourceArray.length);
        System.arraycopy(sourceArray, 0, targetArray, 0, sourceArray.length);
        ReflectionUtils.invokeMethod(target, this.name, new Class<?> [] { this.arrayType }, new Object [] { targetArray });
    }

    @Override
    public String toString() {
        return String.format("array setter %s consuming elements of type %s",
                this.name, this.elementType.getCanonicalName());
    }

}
