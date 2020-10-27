package io.mappingdsl.core.execution;

import io.mappingdsl.core.MappingKey;

public class NoMappingException extends RuntimeException {

    public NoMappingException(MappingKey<?, ?> mappingKey) {
        super("No mapping defined for " + mappingKey);
    }

}
