package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AddressDto {

    @Getter
    @Setter
    private StreetDto street;

    @Getter
    @Setter
    private HouseNumberDto houseNumber;

    @Getter
    @Setter
    private ZipDto zip;

    public AddressDto(StreetDto street, HouseNumberDto houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

}
