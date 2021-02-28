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
import io.mappingdsl.core.tests.fixtures.CountryDto;
import io.mappingdsl.core.tests.fixtures.CountryDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.CountryEntity;
import io.mappingdsl.core.tests.fixtures.CountryEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.CountrySummaryDto;
import io.mappingdsl.core.tests.fixtures.CountrySummaryDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.utils.TestConverters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
    void shouldMapCollectionOfSimpleValuesAndAppend() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(AddressDto.class).useFactory((source, targetType) -> {
                    AddressDto addressDto = new AddressDto();
                    addressDto.setPhoneNumbers(new ArrayList<>(Collections.singletonList("000")));
                    return addressDto;
                })
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                .build();

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("000", "123", "456", "789");
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleIterableTestData")
    void shouldMapIterableOfSimpleValues(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setNationalCurrencies(Collections.singleton("GBP"));

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getNationalCurrencies()).containsExactly("GBP");
    }

    private static Stream<Arguments> simpleIterableTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] iterable mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.nationalCurrencies)
                                .to(CountryDtoMappingDsl.$this.nationalCurrencies)
                                .build()),

                Arguments.of(
                        "[uni] iterable mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.nationalCurrenciesProperty)
                                .to(CountryDtoMappingDsl.$this.nationalCurrenciesProperty)
                                .build()),

                Arguments.of(
                        "[uni] iterable mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getNationalCurrencies)
                                .to(CountryDtoMappingDsl.$this.setNationalCurrencies)
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
    void shouldMapIterableSize() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setNationalCurrencies(Collections.singleton("GBP"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(CountryEntity.class).to(CountrySummaryDto.class)
                .produce(CountryEntityMappingDsl.$this.nationalCurrencies.size())
                .to(CountrySummaryDtoMappingDsl.$this.numberOfCurrencies)
                .build();

        CountrySummaryDto resultCountrySummaryDto = mappingDsl.map(countryEntity, CountrySummaryDto.class);

        assertThat(resultCountrySummaryDto.getNumberOfCurrencies()).isEqualTo(1);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("setCollectionTestData")
    void shouldMapSet(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setBookmarks(new HashSet<>(Collections.singletonList(addressEntity)));

        AddressBookDto resultAddressBookDto = mappingDsl.map(addressBookEntity, AddressBookDto.class);

        assertThat(resultAddressBookDto.getBookmarks().size()).isEqualTo(1);

        AddressDto resultAddressDto = resultAddressBookDto.getBookmarks().iterator().next();

        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressDto.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    private static Stream<Arguments> setCollectionTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] set mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressBookEntity.class).to(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.bookmarks)
                                .usingConverter(TestConverters::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.bookmarks)
                                .build()),

                Arguments.of(
                        "[uni] set mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressBookEntity.class).to(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.bookmarksProperty)
                                .usingConverter(TestConverters::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.bookmarksProperty)
                                .build()),

                Arguments.of(
                        "[uni] set mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressBookEntity.class).to(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.getBookmarks)
                                .usingConverter(TestConverters::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.setBookmarks)
                                .build())
        );
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

    @Test
    void shouldMapIterableElement() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setNationalCurrencies(Collections.singleton("GBP"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(CountryEntity.class).to(CountrySummaryDto.class)
                .produce(CountryEntityMappingDsl.$this.nationalCurrencies.get(0))
                .to(CountrySummaryDtoMappingDsl.$this.primaryCurrency)
                .build();

        CountrySummaryDto resultCountrySummaryDto = mappingDsl.map(countryEntity, CountrySummaryDto.class);

        assertThat(resultCountrySummaryDto.getPrimaryCurrency()).isEqualTo("GBP");
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
