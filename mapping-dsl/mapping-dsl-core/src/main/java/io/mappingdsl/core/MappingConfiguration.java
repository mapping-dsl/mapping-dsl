package io.mappingdsl.core;

import lombok.Getter;
import lombok.Setter;

public class MappingConfiguration {

    @Getter
    @Setter
    private NullHandlingMode nullHandlingMode = NullHandlingMode.PROCEED;

    @Getter
    @Setter
    private MissingMappingHandlingMode missingMappingHandlingMode = MissingMappingHandlingMode.RETURN_NULL;

    public enum NullHandlingMode {
        PROCEED, TERMINATE
    }

    public enum MissingMappingHandlingMode {
        RETURN_NULL, TERMINATE
    }

}
