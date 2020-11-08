package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;

public class StreetDto extends NamedObject {

    @Getter
    private HouseNumberDto houseNumber;

}
