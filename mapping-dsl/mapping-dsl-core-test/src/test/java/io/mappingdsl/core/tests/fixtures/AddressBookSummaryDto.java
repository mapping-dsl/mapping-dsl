package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

public class AddressBookSummaryDto {

    @Getter
    @Setter
    private AddressDto ownerAddress;

    @Getter
    @Setter
    private Integer numberOfAddresses;

    @Getter
    @Setter
    private Integer numberOfBookmarks;

}
