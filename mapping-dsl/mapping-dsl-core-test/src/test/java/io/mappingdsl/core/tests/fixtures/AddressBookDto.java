package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

public class AddressBookDto {

    @Getter
    @Setter
    private List<AddressDto> addresses;

    @Getter
    @Setter
    private Set<AddressDto> bookmarks;

}
