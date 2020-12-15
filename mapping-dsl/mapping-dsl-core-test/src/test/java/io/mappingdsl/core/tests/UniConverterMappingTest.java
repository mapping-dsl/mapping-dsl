package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.Geolocation;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniConverterMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("primitiveFieldTestData")
    void shouldMapConvertedPrimitiveField(String testName, MappingDsl mappingDsl) {
        ZipEntity zipEntity = new ZipEntity(123456);
        ZipDto zipDto = mappingDsl.map(zipEntity, ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

    private static Stream<Arguments> primitiveFieldTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] converter over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .build()),

                Arguments.of(
                        "[uni] converter over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .build()),

                Arguments.of(
                        "[uni] converter over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexFieldTestData")
    void shouldMapConvertedComplexField(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(addressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(addressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(addressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> complexFieldTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] converter over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(UniConverterMappingTest::convertHouseNumber)
                                .to(AddressDtoMappingDsl.$this.houseNumber)
                                .build()),

                Arguments.of(
                        "[uni] converter over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.nameProperty)
                                .to(AddressDtoMappingDsl.$this.street.nameProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(UniConverterMappingTest::convertHouseNumber)
                                .to(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .build()),

                Arguments.of(
                        "[uni] converter over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.getName)
                                .to(AddressDtoMappingDsl.$this.street.setName)
                                .produce(AddressEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(UniConverterMappingTest::convertHouseNumber)
                                .to(AddressDtoMappingDsl.$this.setHouseNumber)
                                .build())
        );
    }

    private static HouseNumberDto convertHouseNumber(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

    @Test
    void shouldMapConvertedCollectionElements() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                .usingConverter(phoneNumber -> "+" + phoneNumber)
                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                .build();

        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(addressDto.getPhoneNumbers()).containsExactly("+123", "+456", "+789");
    }

}
