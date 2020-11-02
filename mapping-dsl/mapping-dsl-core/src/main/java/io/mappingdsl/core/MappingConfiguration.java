package io.mappingdsl.core;

import lombok.Getter;
import lombok.Setter;

import static io.mappingdsl.core.MappingConfiguration.NullHandlingMode.PROCEED;

public class MappingConfiguration {

    @Getter
    @Setter
    private NullHandlingMode nullHandlingMode = PROCEED;

    public enum NullHandlingMode {
        PROCEED, TERMINATE
    }

}
