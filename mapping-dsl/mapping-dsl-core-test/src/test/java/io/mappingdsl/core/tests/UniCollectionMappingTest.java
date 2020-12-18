package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressBookDto;
import io.mappingdsl.core.tests.fixtures.AddressBookDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressBookEntity;
import io.mappingdsl.core.tests.fixtures.AddressBookEntityMappingDsl;
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

        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(addressDto.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

    @Test
    void shouldMapCollectionOfComplexValues() {
        AddressBookEntity addressBookEntity = new AddressBookEntity();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);

        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

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

        AddressBookDto addressBookDto = mappingDsl.map(addressBookEntity, AddressBookDto.class);

        assertThat(addressBookDto.getAddresses().size()).isEqualTo(2);

        AddressDto addressDto = addressBookDto.getAddresses().get(0);

        assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressDto.getPhoneNumbers()).containsExactly("123", "456");

        AddressDto anotherAddressDto = addressBookDto.getAddresses().get(1);

        assertThat(anotherAddressDto.getStreet().getName()).isEqualTo("Privet Drive");
        assertThat(anotherAddressDto.getHouseNumber().getNumber()).isEqualTo(4);
    }

}
