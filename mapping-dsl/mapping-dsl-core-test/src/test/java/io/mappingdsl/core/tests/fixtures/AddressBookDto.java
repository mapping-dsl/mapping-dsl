package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AddressBookDto {

    @Getter
    @Setter
    private List<AddressDto> addresses;

}
