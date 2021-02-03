package io.mappingdsl.core.config;

import java.util.HashMap;
import java.util.Map;

public class HintConfiguration {

    private final Map<Class<?>, Class<?>> hints = new HashMap<>();

    public <T> void registerHint(Class<T> type, Class<? extends T> hint) {
        if (hasHint(type)) {
            throw new IllegalStateException("Hint has been already set for type: " + type.getCanonicalName());
        }

        this.hints.put(type, hint);
    }

    public boolean hasHint(Class<?> type) {
        return this.hints.containsKey(type);
    }

    public <T> Class<? extends T> getHint(Class<T> type) {
        return (Class<? extends T>) this.hints.get(type);
    }

}
