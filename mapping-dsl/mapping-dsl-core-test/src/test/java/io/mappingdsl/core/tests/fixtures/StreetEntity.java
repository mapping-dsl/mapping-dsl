package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class StreetEntity {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private HouseNumberEntity houseNumber;

}
