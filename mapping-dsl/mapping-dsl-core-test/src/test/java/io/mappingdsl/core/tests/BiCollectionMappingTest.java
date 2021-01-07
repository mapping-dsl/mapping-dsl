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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiCollectionMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleTestData")
    void shouldMapCollectionOfSimpleValues(String testName, MappingDsl mappingDsl) {
        // forward mapping
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("123", "456", "789");

        // backward mapping
        AddressDto addressDto = new AddressDto();
        addressDto.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressEntity resultAddressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

    private static Stream<Arguments> simpleTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.phoneNumbers)
                                .with(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleForwardTestData")
    void shouldForwardMapCollectionOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

    private static Stream<Arguments> simpleForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleBackwardTestData")
    void shouldBackwardMapCollectionOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressDto addressDto = new AddressDto();
        addressDto.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressEntity resultAddressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

    private static Stream<Arguments> simpleBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.phoneNumbers)
                                .from(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexTestData")
    void shouldMapCollectionOfComplexValues(String testName, MappingDsl mappingDsl) {
        // prepare forward mapping
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);
        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setAddresses(Arrays.asList(addressEntity, anotherAddressEntity));

        // forward mapping
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

        // prepare backward mapping
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B");
        AddressDto addressDto = new AddressDto(streetDto, houseNumberDto);
        addressDto.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetDto anotherStreetDto = new StreetDto("Privet Drive");
        HouseNumberDto anotherHouseNumberDto = new HouseNumberDto(4);
        AddressDto anotherAddressDto = new AddressDto(anotherStreetDto, anotherHouseNumberDto);

        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setAddresses(Arrays.asList(addressDto, anotherAddressDto));

        // backward mapping
        AddressBookEntity resultAddressBookEntity = mappingDsl.map(addressBookDto, AddressBookEntity.class);
        assertThat(resultAddressBookEntity.getAddresses().size()).isEqualTo(2);

        AddressEntity resultAddressEntity = resultAddressBookEntity.getAddresses().get(0);
        assertThat(resultAddressEntity.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(resultAddressEntity.getPhoneNumbers()).containsExactly("123", "456");

        AddressEntity resultAnotherAddressEntity = addressBookEntity.getAddresses().get(1);
        assertThat(resultAnotherAddressEntity.getStreet().getName()).isEqualTo("Privet Drive");
        assertThat(resultAnotherAddressEntity.getHouseNumber().getNumber()).isEqualTo(4);
    }

    private static Stream<Arguments> complexTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] collection of complex values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressBookEntity.class).and(AddressBookDto.class)
                                .bind(AddressBookEntityMappingDsl.$this.addresses)
                                .usingMapping()
                                .with(AddressBookDtoMappingDsl.$this.addresses)

                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street)
                                .usingMapping()
                                .with(AddressDtoMappingDsl.$this.street)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .with(AddressDtoMappingDsl.$this.houseNumber)
                                .bind(AddressEntityMappingDsl.$this.phoneNumbers)
                                .with(AddressDtoMappingDsl.$this.phoneNumbers)

                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.number)
                                .with(HouseNumberDtoMappingDsl.$this.number)
                                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                                .with(HouseNumberDtoMappingDsl.$this.suffix)
                                .build())
          );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexForwardTestData")
    void shouldForwardMapCollectionOfComplexValues(String testName, MappingDsl mappingDsl) {
        // prepare forward mapping
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);
        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

        AddressBookEntity addressBookEntity = new AddressBookEntity();
        addressBookEntity.setAddresses(Arrays.asList(addressEntity, anotherAddressEntity));

        // forward mapping
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

    private static Stream<Arguments> complexForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] collection of complex values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressBookEntity.class).and(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.addresses)
                                .usingMapping()
                                .to(AddressBookDtoMappingDsl.$this.addresses)

                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.street)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.houseNumber)
                                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                                .to(AddressDtoMappingDsl.$this.phoneNumbers)

                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexBackwardTestData")
    void shouldBackwardMapCollectionOfComplexValues(String testName, MappingDsl mappingDsl) {
        // prepare backward mapping
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B");
        AddressDto addressDto = new AddressDto(streetDto, houseNumberDto);
        addressDto.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetDto anotherStreetDto = new StreetDto("Privet Drive");
        HouseNumberDto anotherHouseNumberDto = new HouseNumberDto(4);
        AddressDto anotherAddressDto = new AddressDto(anotherStreetDto, anotherHouseNumberDto);

        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setAddresses(Arrays.asList(addressDto, anotherAddressDto));

        // backward mapping
        AddressBookEntity resultAddressBookEntity = mappingDsl.map(addressBookDto, AddressBookEntity.class);
        assertThat(resultAddressBookEntity.getAddresses().size()).isEqualTo(2);

        AddressEntity resultAddressEntity = resultAddressBookEntity.getAddresses().get(0);
        assertThat(resultAddressEntity.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(resultAddressEntity.getPhoneNumbers()).containsExactly("123", "456");

        AddressEntity resultAnotherAddressEntity = resultAddressBookEntity.getAddresses().get(1);
        assertThat(resultAnotherAddressEntity.getStreet().getName()).isEqualTo("Privet Drive");
        assertThat(resultAnotherAddressEntity.getHouseNumber().getNumber()).isEqualTo(4);
    }

    private static Stream<Arguments> complexBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] collection of complex values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressBookEntity.class).and(AddressBookDto.class)
                                .consume(AddressBookEntityMappingDsl.$this.addresses)
                                .usingMapping()
                                .from(AddressBookDtoMappingDsl.$this.addresses)

                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street)
                                .usingMapping()
                                .from(AddressDtoMappingDsl.$this.street)
                                .consume(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .from(AddressDtoMappingDsl.$this.houseNumber)
                                .consume(AddressEntityMappingDsl.$this.phoneNumbers)
                                .from(AddressDtoMappingDsl.$this.phoneNumbers)

                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.name)
                                .from(StreetDtoMappingDsl.$this.name)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.number)
                                .from(HouseNumberDtoMappingDsl.$this.number)
                                .consume(HouseNumberEntityMappingDsl.$this.suffix)
                                .from(HouseNumberDtoMappingDsl.$this.suffix)
                                .build())
        );
    }

}
