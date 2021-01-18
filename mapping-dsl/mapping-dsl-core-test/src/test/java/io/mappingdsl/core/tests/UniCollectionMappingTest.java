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
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.utils.TestConverters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniCollectionMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleValuesTestData")
    void shouldMapCollectionOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

    private static Stream<Arguments> simpleValuesTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] collection mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build()),

                Arguments.of(
                        "[uni] collection mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.phoneNumbersProperty)
                                .to(AddressDtoMappingDsl.$this.phoneNumbersProperty)
                                .build()),

                Arguments.of(
                        "[uni] collection mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.getPhoneNumbers)
                                .to(AddressDtoMappingDsl.$this.setPhoneNumbers)
                                .build())
        );
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
    void shouldMapCollectionElement() {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setAddresses(Collections.singletonList(addressEntity));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressBookEntity.class).to(AddressBookSummaryDto.class)
                .produce(AddressBookEntityMappingDsl.$this.addresses.get(0))
                .usingConverter(TestConverters::convertAddressEntity)
                .to(AddressBookSummaryDtoMappingDsl.$this.ownerAddress)
                .build();

        AddressBookSummaryDto resultAddressBookSummaryDto =
                mappingDsl.map(addressBookEntity, AddressBookSummaryDto.class);

        AddressDto resultAddressDto = resultAddressBookSummaryDto.getOwnerAddress();
        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressDto.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexValuesTestData")
    void shouldMapCollectionOfComplexValues(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);
        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setAddresses(Arrays.asList(addressEntity, anotherAddressEntity));

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

    private static Stream<Arguments> complexValuesTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] collection mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressBookEntity.class).to(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.addresses)
                                .usingConverter(TestConverters::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.addresses)
                                .build()),

                Arguments.of(
                        "[uni] collection mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressBookEntity.class).to(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.addressesProperty)
                                .usingConverter(TestConverters::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.addressesProperty)
                                .build()),

                Arguments.of(
                        "[uni] collection mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressBookEntity.class).to(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.getAddresses)
                                .usingConverter(TestConverters::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.setAddresses)
                                .build())
        );
    }

}
