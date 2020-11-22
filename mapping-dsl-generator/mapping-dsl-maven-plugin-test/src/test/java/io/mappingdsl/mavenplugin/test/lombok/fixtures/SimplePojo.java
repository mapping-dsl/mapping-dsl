package io.mappingdsl.mavenplugin.test.lombok.fixtures;

import lombok.Getter;
import lombok.Setter;

public class SimplePojo {

    @Getter
    private String gettableValue;

    @Setter
    private String settableValue;

    @Getter
    @Setter
    private String propertyValue;

}
