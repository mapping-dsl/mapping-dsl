package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

public class AddressBookEntity {

    @Getter
    @Setter
    private List<AddressEntity> addresses;

    @Getter
    @Setter
    private Set<AddressEntity> bookmarks;

}
