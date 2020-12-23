package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.execution.NoMappingException;
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
import io.mappingdsl.core.tests.utils.BiMappingTestFlow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiConverterMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterTestData")
    void shouldMapUsingSimpleConverter(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        // forward mapping
        ZipDto zipDto;

        try {
            zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
        }
        catch (NoMappingException ignore) {
            zipDto = null;
        }

        if (testFlow.isForwardMapped()) {
            assertThat(zipDto.getCode()).isEqualTo("123456");
        }
        else {
            assertThat(zipDto).isNull();
        }

        // refresh test entity for backward mapping
        zipDto = new ZipDto("123456");

        // backward mapping
        ZipEntity zipEntity;
        try {
            zipEntity = mappingDsl.map(zipDto, ZipEntity.class);
        }
        catch (NoMappingException ignore) {
            zipEntity = null;
        }

        if (testFlow.isBackwardMapped()) {
            assertThat(zipEntity.getCode()).isEqualTo(123456);
        }
        else {
            assertThat(zipEntity).isNull();
        }
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] lambda converters over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.code)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.code)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.code)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.codeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.setCode)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.getCode)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterTestData")
    void shouldMapUsingComplexConverter(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        // forward mapping
        AddressDto addressDto;

        try {
            addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        }
        catch (NoMappingException ignore) {
            addressDto = null;
        }

        if (testFlow.isForwardMapped()) {
            assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");
            assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(addressDto.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(addressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(addressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(addressDto).isNull();
        }

        // refresh test entity for backward mapping
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539));
        addressDto = new AddressDto(streetDto, houseNumberDto);

        // backward mapping
        try {
            addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        }
        catch (NoMappingException ignore) {
            addressEntity = null;
        }

        if (testFlow.isBackwardMapped()) {
            assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");
            assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(addressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(addressEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(addressEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(addressEntity).isNull();
        }
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build())
        );
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
