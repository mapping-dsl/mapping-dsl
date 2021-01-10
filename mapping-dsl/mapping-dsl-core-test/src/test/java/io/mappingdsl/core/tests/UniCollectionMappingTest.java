package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressBookDto;
import io.mappingdsl.core.tests.fixtures.AddressBookDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressBookEntity;
import io.mappingdsl.core.tests.fixtures.AddressBookEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressBookSummaryDto;
import io.mappingdsl.core.tests.fixtures.AddressBookSummaryDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class UniCollectionMappingTest {

    @Test
    void shouldMapCollectionOfSimpleValues() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                .build();

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

    @Test
    void shouldMapCollectionSize() {
        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setAddresses(Collections.nCopies(42, new AddressEntity()));
        addressBookEntity.setBookmarks(new HashSet<>(Arrays.asList(new AddressEntity(), new AddressEntity())));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressBookEntity.class).to(AddressBookSummaryDto.class)
                .produce(AddressBookEntityMappingDsl.$this.addresses.size())
                .to(AddressBookSummaryDtoMappingDsl.$this.numberOfAddresses)
                .produce(AddressBookEntityMappingDsl.$this.bookmarks.size())
                .to(AddressBookSummaryDtoMappingDsl.$this.numberOfBookmarks)
                .build();

        AddressBookSummaryDto resultAddressBookSummaryDto =
                mappingDsl.map(addressBookEntity, AddressBookSummaryDto.class);

        assertThat(resultAddressBookSummaryDto.getNumberOfAddresses()).isEqualTo(42);
        assertThat(resultAddressBookSummaryDto.getNumberOfBookmarks()).isEqualTo(2);
    }

    @Test
    void shouldMapCollectionOfComplexValues() {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);
        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setAddresses(Arrays.asList(addressEntity, anotherAddressEntity));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressBookEntity.class).to(AddressBookDto.class)
                .produce(AddressBookEntityMappingDsl.$this.addresses)
                .usingMapping()
                .to(AddressBookDtoMappingDsl.$this.addresses)

                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.street)
                .usingMapping()
                .to(AddressDtoMappingDsl.$this.street)
                .produce(AddressEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .to(AddressDtoMappingDsl.$this.houseNumber)
                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                .to(AddressDtoMappingDsl.$this.phoneNumbers)

                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)

                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .produce(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        AddressBookDto resultAddressBookDto = mappingDsl.map(addressBookEntity, AddressBookDto.class);
        assertThat(resultAddressBookDto.getAddresses().size()).isEqualTo(2);

        AddressDto resultAddressDto = resultAddressBookDto.getAddresses().get(0);

        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("123", "456");

        AddressDto resultAnotherAddressDto = resultAddressBookDto.getAddresses().get(1);
        assertThat(resultAnotherAddressDto.getStreet().getName()).isEqualTo("Privet Drive");
        assertThat(resultAnotherAddressDto.getHouseNumber().getNumber()).isEqualTo(4);
    }

}
