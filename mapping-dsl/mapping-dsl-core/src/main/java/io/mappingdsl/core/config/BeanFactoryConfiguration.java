package io.mappingdsl.core.config;

import io.mappingdsl.core.execution.MappingBeanFactory;

import java.util.HashMap;
import java.util.Map;

public class BeanFactoryConfiguration {

    private final Map<Class<?>, MappingBeanFactory<?>> beanFactories = new HashMap<>();

    public <T> void registerFactory(Class<T> type, MappingBeanFactory<T> factory) {
        if (hasFactory(type)) {
            throw new IllegalStateException("Bean Factory has been already set for type: " + type.getCanonicalName());
        }

        this.beanFactories.put(type, factory);
    }

    public boolean hasFactory(Class<?> type) {
        return this.beanFactories.containsKey(type);
    }

    public <T> MappingBeanFactory<T> getFactory(Class<T> type) {
        return (MappingBeanFactory<T>) this.beanFactories.get(type);
    }

}
