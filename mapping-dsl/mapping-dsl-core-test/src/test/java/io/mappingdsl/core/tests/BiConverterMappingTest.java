package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.execution.NoMappingException;
import io.mappingdsl.core.tests.fixtures.AddressBookDto;
import io.mappingdsl.core.tests.fixtures.AddressBookDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressBookEntity;
import io.mappingdsl.core.tests.fixtures.AddressBookEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.Geolocation;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiConverterMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterTestData")
    void shouldMapUsingSimpleConverter(String testName, MappingDsl mappingDsl) {
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
        assertThat(zipDto.getCode()).isEqualTo("123456");

        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);
        assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

    private static Stream<Arguments> simpleConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] lambda converters over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.code)
                                .build()),

                Arguments.of(
                        "[bi] lambda converters over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.code)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterForwardTestData")
    void shouldForwardMapUsingSimpleConverter(String testName, MappingDsl mappingDsl) {
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

    private static Stream<Arguments> simpleConverterForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterBackwardTestData")
    void shouldBackwardMapUsingSimpleConverter(String testName, MappingDsl mappingDsl) {
        ZipDto zipDto = new ZipDto("123456");
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);
        assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

    private static Stream<Arguments> simpleConverterBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.code)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.code)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.codeProperty)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.setCode)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.getCode)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterTestData")
    void shouldMapUsingComplexConverter(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        // forward mapping
        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(addressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // refresh test entity for backward mapping
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539));
        addressDto = new AddressDto(streetDto, houseNumberDto);

        addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(addressEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> complexConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] encapsulated converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.name)
                                .with(AddressDtoMappingDsl.$this.street.name)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(houseNumberConverter)
                                .with(AddressDtoMappingDsl.$this.houseNumber)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.nameProperty)
                                .with(AddressDtoMappingDsl.$this.street.nameProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(houseNumberConverter)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.name)
                                .with(AddressDtoMappingDsl.$this.street.name)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverters(
                                        BiConverterMappingTest::convertHouseNumberEntity,
                                        BiConverterMappingTest::convertHouseNumberDto)
                                .with(AddressDtoMappingDsl.$this.houseNumber)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.nameProperty)
                                .with(AddressDtoMappingDsl.$this.street.nameProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverters(
                                        BiConverterMappingTest::convertHouseNumberEntity,
                                        BiConverterMappingTest::convertHouseNumberDto)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterForwardTestData")
    void shouldForwardMapUsingComplexConverter(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        // forward mapping
        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(addressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> complexConverterForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.houseNumber)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.nameProperty)
                                .to(AddressDtoMappingDsl.$this.street.nameProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.getName)
                                .to(AddressDtoMappingDsl.$this.street.setName)
                                .produce(AddressEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.setHouseNumber)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterBackwardTestData")
    void shouldBackwardMapUsingComplexConverter(String testName, MappingDsl mappingDsl) {
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539));
        AddressDto addressDto = new AddressDto(streetDto, houseNumberDto);

        AddressEntity addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(addressEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> complexConverterBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.name)
                                .from(AddressDtoMappingDsl.$this.street.name)
                                .consume(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberDto)
                                .from(AddressDtoMappingDsl.$this.houseNumber)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.nameProperty)
                                .from(AddressDtoMappingDsl.$this.street.nameProperty)
                                .consume(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberDto)
                                .from(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.setName)
                                .from(AddressDtoMappingDsl.$this.street.getName)
                                .consume(AddressEntityMappingDsl.$this.setHouseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberDto)
                                .from(AddressDtoMappingDsl.$this.getHouseNumber)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleCollectionConverterTestData")
    void shouldMapConvertedCollectionElements(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        // forward mapping
        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(addressDto.getPhoneNumbers()).containsExactly("+123", "+456", "+789");

        // refresh test entity for backward mapping
        addressDto = new AddressDto();
        addressDto.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(addressEntity.getPhoneNumbers()).containsExactly("123;", "456;", "789;");
    }

    private static Stream<Arguments> simpleCollectionConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.phoneNumbers)
                                .usingConverter(phoneNumberConverter)
                                .with(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build())
        );
    }
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleCollectionConverterForwardTestData")
    void shouldForwardMapConvertedCollectionElements(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(addressDto.getPhoneNumbers()).containsExactly("+123", "+456", "+789");
    }

    private static Stream<Arguments> simpleCollectionConverterForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                                .usingConverter(phoneNumber -> "+" + phoneNumber)
                                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleCollectionConverterBackwardTestData")
    void shouldBackwardMapConvertedCollectionElements(String testName, MappingDsl mappingDsl) {
        AddressDto addressDto = new AddressDto();
        addressDto.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        AddressEntity addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(addressEntity.getPhoneNumbers()).containsExactly("123;", "456;", "789;");
    }

    private static Stream<Arguments> simpleCollectionConverterBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.phoneNumbers)
                                .usingConverter((Converter<String, String>) phoneNumber -> phoneNumber + ";")
                                .from(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexTestData")
    void shouldMapCollectionOfComplexConvertedValues(String testName, MappingDsl mappingDsl) {
        AddressBookEntity addressBookEntity = new AddressBookEntity();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);

        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

        addressBookEntity.setAddresses(Arrays.asList(addressEntity, anotherAddressEntity));

        // forward mapping
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

        // refresh test entity for backward mapping
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B");

        addressDto = new AddressDto(streetDto, houseNumberDto);
        addressDto.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetDto anotherStreetDto = new StreetDto("Privet Drive");
        HouseNumberDto anotherHouseNumberDto = new HouseNumberDto(4);

        anotherAddressDto = new AddressDto(anotherStreetDto, anotherHouseNumberDto);

        addressBookDto = new AddressBookDto();
        addressBookDto.setAddresses(Arrays.asList(addressDto, anotherAddressDto));

        addressBookEntity = mappingDsl.map(addressBookDto, AddressBookEntity.class);
        assertThat(addressBookEntity.getAddresses().size()).isEqualTo(2);

        addressEntity = addressBookEntity.getAddresses().get(0);

        assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressEntity.getPhoneNumbers()).containsExactly("123", "456");

        anotherAddressEntity = addressBookEntity.getAddresses().get(1);

        assertThat(anotherAddressEntity.getStreet().getName()).isEqualTo("Privet Drive");
        assertThat(anotherAddressEntity.getHouseNumber().getNumber()).isEqualTo(4);
    }

    private static Stream<Arguments> complexTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] collection of complex values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressBookEntity.class).and(AddressBookDto.class)
                                .bind(AddressBookEntityMappingDsl.$this.addresses)
                                .usingConverter(addressConverter)
                                .with(AddressBookDtoMappingDsl.$this.addresses)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexForwardTestData")
    void shouldForwardMapCollectionOfComplexConvertedValues(String testName, MappingDsl mappingDsl) {
        AddressBookEntity addressBookEntity = new AddressBookEntity();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetEntity anotherStreetEntity = new StreetEntity("Privet Drive");
        HouseNumberEntity anotherHouseNumberEntity = new HouseNumberEntity(4);

        AddressEntity anotherAddressEntity = new AddressEntity(anotherStreetEntity, anotherHouseNumberEntity);

        addressBookEntity.setAddresses(Arrays.asList(addressEntity, anotherAddressEntity));

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

    private static Stream<Arguments> complexForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] collection of complex values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressBookEntity.class).and(AddressBookDto.class)
                                .produce(AddressBookEntityMappingDsl.$this.addresses)
                                .usingConverter(BiConverterMappingTest::convertAddressEntity)
                                .to(AddressBookDtoMappingDsl.$this.addresses)
                                .build())
        );
    }
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexBackwardTestData")
    void shouldBackwardMapCollectionOfComplexConvertedValues(String testName, MappingDsl mappingDsl) {
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B");

        AddressDto addressDto = new AddressDto(streetDto, houseNumberDto);
        addressDto.setPhoneNumbers(Arrays.asList("123", "456"));

        StreetDto anotherStreetDto = new StreetDto("Privet Drive");
        HouseNumberDto anotherHouseNumberDto = new HouseNumberDto(4);

        AddressDto anotherAddressDto = new AddressDto(anotherStreetDto, anotherHouseNumberDto);

        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setAddresses(Arrays.asList(addressDto, anotherAddressDto));

        AddressBookEntity addressBookEntity = mappingDsl.map(addressBookDto, AddressBookEntity.class);

        assertThat(addressBookEntity.getAddresses().size()).isEqualTo(2);

        AddressEntity addressEntity = addressBookEntity.getAddresses().get(0);

        assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressEntity.getPhoneNumbers()).containsExactly("123", "456");

        AddressEntity anotherAddressEntity = addressBookEntity.getAddresses().get(1);

        assertThat(anotherAddressEntity.getStreet().getName()).isEqualTo("Privet Drive");
        assertThat(anotherAddressEntity.getHouseNumber().getNumber()).isEqualTo(4);
    }

    private static Stream<Arguments> complexBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] collection of complex values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressBookEntity.class).and(AddressBookDto.class)
                                .consume(AddressBookEntityMappingDsl.$this.addresses)
                                .usingConverter(BiConverterMappingTest::convertAddressDto)
                                .from(AddressBookDtoMappingDsl.$this.addresses)
                                .build())
        );
    }

    private static final BiConverter<String, String> phoneNumberConverter = new BiConverter<String, String>() {

        @Override
        public String convertForward(String source) {
            return "+" + source;
        }

        @Override
        public String convertBackward(String target) {
            return target + ";";
        }
    };

    private static final BiConverter<AddressEntity, AddressDto> addressConverter =
            new BiConverter<AddressEntity, AddressDto>() {

                @Override
                public AddressDto convertForward(AddressEntity source) {
                    return convertAddressEntity(source);
                }

                @Override
                public AddressEntity convertBackward(AddressDto target) {
                    return convertAddressDto(target);
                }

            };

    private static AddressDto convertAddressEntity(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto(
                convertStreetEntity(addressEntity.getStreet()),
                convertHouseNumberEntity(addressEntity.getHouseNumber()));

        addressDto.setPhoneNumbers(addressEntity.getPhoneNumbers());

        return addressDto;
    }

    private static StreetDto convertStreetEntity(StreetEntity streetEntity) {
        return new StreetDto(streetEntity.getName());
    }

    private static AddressEntity convertAddressDto(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity(
                convertStreetDto(addressDto.getStreet()),
                convertHouseNumberDto(addressDto.getHouseNumber()));

        addressEntity.setPhoneNumbers(addressDto.getPhoneNumbers());

        return addressEntity;
    }

    private static StreetEntity convertStreetDto(StreetDto streetDto) {
        return new StreetEntity(streetDto.getName());
    }


    private static final BiConverter<Integer, String> primitiveConverter =
            new BiConverter<Integer, String>() {

                @Override
                public String convertForward(Integer source) {
                    return String.valueOf(source);
                }

                @Override
                public Integer convertBackward(String target) {
                    return Integer.valueOf(target);
                }

            };

    private static final BiConverter<HouseNumberEntity, HouseNumberDto> houseNumberConverter =
            new BiConverter<HouseNumberEntity, HouseNumberDto>() {

                @Override
                public HouseNumberDto convertForward(HouseNumberEntity source) {
                    return convertHouseNumberEntity(source);
                }

                @Override
                public HouseNumberEntity convertBackward(HouseNumberDto target) {
                    return convertHouseNumberDto(target);
                }

            };

    private static HouseNumberDto convertHouseNumberEntity(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

    private static HouseNumberEntity convertHouseNumberDto(HouseNumberDto houseNumberDto) {
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity();
        houseNumberEntity.setNumber(houseNumberDto.getNumber());
        houseNumberEntity.setSuffix(houseNumberDto.getSuffix());
        houseNumberEntity.setGeolocation(houseNumberDto.getGeolocation());
        return houseNumberEntity;
    }

}
