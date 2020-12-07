package io.mappingdsl.core.execution;

public interface MappingBeanFactory<T> {

    T create(Object source, Class<T> targetType);

}
