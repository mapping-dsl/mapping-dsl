package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.tests.fixtures.Geolocation;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
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
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        if (testFlow.isForwardMapped()) {
            assertThat(zipDto.getCode()).isEqualTo("123456");
        }
        else {
            assertThat(zipDto).isNull();
        }

        // refresh test entity for backward mapping
        zipDto = new ZipDto("123456");

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);

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
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        // forward mapping
        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        if (testFlow.isForwardMapped()) {
            assertThat(streetDto.getName()).isEqualTo("Baker Street");
            assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(streetDto).isNull();
        }

        // refresh test entity for backward mapping
        streetDto = new StreetDto("Baker Street");
        streetDto.setHouseNumber(new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539)));

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        if (testFlow.isBackwardMapped()) {
            assertThat(streetEntity.getName()).isEqualTo("Baker Street");
            assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(streetEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(streetEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(streetEntity).isNull();
        }
    }

    private static Stream<Arguments> complexConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] encapsulated converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(houseNumberConverter)
                                .with(StreetDtoMappingDsl.$this.houseNumber)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(houseNumberConverter)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverters(
                                        BiConverterMappingTest::convertHouseNumberEntity,
                                        BiConverterMappingTest::convertHouseNumberDto)
                                .with(StreetDtoMappingDsl.$this.houseNumber)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverters(
                                        BiConverterMappingTest::convertHouseNumberEntity,
                                        BiConverterMappingTest::convertHouseNumberDto)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberEntity)
                                .to(StreetDtoMappingDsl.$this.houseNumber)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.nameProperty)
                                .to(StreetDtoMappingDsl.$this.nameProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberEntity)
                                .to(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.getName)
                                .to(StreetDtoMappingDsl.$this.setName)
                                .produce(StreetEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberEntity)
                                .to(StreetDtoMappingDsl.$this.setHouseNumber)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.name)
                                .from(StreetDtoMappingDsl.$this.name)
                                .consume(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberDto)
                                .from(StreetDtoMappingDsl.$this.houseNumber)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.nameProperty)
                                .from(StreetDtoMappingDsl.$this.nameProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberDto)
                                .from(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.setName)
                                .from(StreetDtoMappingDsl.$this.getName)
                                .consume(StreetEntityMappingDsl.$this.setHouseNumber)
                                .usingConverter(BiConverterMappingTest::convertHouseNumberDto)
                                .from(StreetDtoMappingDsl.$this.getHouseNumber)
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
