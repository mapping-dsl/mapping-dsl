package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AddressBookEntity {

    @Getter
    @Setter
    private List<AddressEntity> addresses;

}
