package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

public class AddressBookSummaryEntity {

    @Getter
    @Setter
    private AddressEntity ownerAddress;

    @Getter
    @Setter
    private Integer numberOfAddresses;

    @Getter
    @Setter
    private Integer numberOfBookmarks;

}
