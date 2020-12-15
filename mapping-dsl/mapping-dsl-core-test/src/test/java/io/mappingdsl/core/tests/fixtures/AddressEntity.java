package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class AddressEntity {

    @Getter
    @Setter
    private StreetEntity street;

    @Getter
    @Setter
    private HouseNumberEntity houseNumber;

    @Getter
    @Setter
    private ZipEntity zip;

    @Getter
    @Setter
    private List<String> phoneNumbers;

    public AddressEntity(StreetEntity street, HouseNumberEntity houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

}
