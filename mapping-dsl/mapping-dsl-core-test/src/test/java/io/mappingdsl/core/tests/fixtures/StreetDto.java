package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

public class StreetDto extends NamedObject {

    @Getter
    @Setter
    private HouseNumberDto houseNumber;

}
