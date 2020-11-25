package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class StreetDto extends NamedObject {

    @Getter
    @Setter
    private HouseNumberDto houseNumber;

    public StreetDto(String name) {
        super(name);
    }

}
