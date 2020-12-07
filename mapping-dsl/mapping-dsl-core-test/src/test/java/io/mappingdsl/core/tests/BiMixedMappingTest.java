package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiCondition;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
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

class BiMixedMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterTestData")
    void shouldMapUsingSimpleConverterWhenConditionAllows(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        if (testFlow.isForwardMapped()) {
            // forward mapping + condition allows mapping
            ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
            assertThat(zipDto.getCode()).isNotBlank();

            // forward mapping + condition does not allow mapping
            zipDto = mappingDsl.map(new ZipEntity(123), ZipDto.class);
            assertThat(zipDto.getCode()).isNull();
        }

        // backward mapping
        if (testFlow.isBackwardMapped()) {
            ZipEntity zipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);
            assertThat(zipEntity.getCode()).isPositive();

            zipEntity = mappingDsl.map(new ZipDto("123"), ZipEntity.class);
            assertThat(zipEntity.getCode()).isNull();
        }
    }

    private static Stream<Arguments> simpleConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] lambda converters over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] lambda converters over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .when(source -> source > 1000)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.code)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.code)
                                .when(target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.codeProperty)
                                .when(target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.setCode)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.getCode)
                                .when(target -> target.length() > 5)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterTestData")
    void shouldMapUsingComplexConverterWhenConditionAllows(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        if (testFlow.isForwardMapped()) {
            StreetDto streetDto = mappingDsl.map(new StreetEntity(null, new HouseNumberEntity(221)), StreetDto.class);
            assertThat(streetDto.getHouseNumber().getNumber()).isPositive();

            streetDto = mappingDsl.map(new StreetEntity(null, new HouseNumberEntity(-221)), StreetDto.class);
            assertThat(streetDto.getHouseNumber()).isNull();
        }

        if (testFlow.isBackwardMapped()) {
            StreetEntity streetEntity = mappingDsl.map(new StreetDto(null, new HouseNumberDto(221)), StreetEntity.class);
            assertThat(streetEntity.getHouseNumber().getNumber()).isPositive();

            streetEntity = mappingDsl.map(new StreetDto(null, new HouseNumberDto(-221)), StreetEntity.class);
            assertThat(streetEntity.getHouseNumber()).isNull();
        }
    }

    private static Stream<Arguments> complexConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] encapsulated converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(houseNumberConverter)
                                .with(StreetDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberCondition)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter mapping over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(houseNumberConverter)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberCondition)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverters(
                                        BiMixedMappingTest::convertHouseNumberEntity,
                                        BiMixedMappingTest::convertHouseNumberDto)
                                .with(StreetDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberCondition)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter mapping over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverters(
                                        BiMixedMappingTest::convertHouseNumberEntity,
                                        BiMixedMappingTest::convertHouseNumberDto)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberCondition)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberEntity)
                                .to(StreetDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 100)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.nameProperty)
                                .to(StreetDtoMappingDsl.$this.nameProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberEntity)
                                .to(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 100)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.getName)
                                .to(StreetDtoMappingDsl.$this.setName)
                                .produce(StreetEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberEntity)
                                .to(StreetDtoMappingDsl.$this.setHouseNumber)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 100)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.name)
                                .from(StreetDtoMappingDsl.$this.name)
                                .consume(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberDto)
                                .from(StreetDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberDto -> houseNumberDto.getNumber() > 100)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.nameProperty)
                                .from(StreetDtoMappingDsl.$this.nameProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberDto)
                                .from(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberDto -> houseNumberDto.getNumber() > 100)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.setName)
                                .from(StreetDtoMappingDsl.$this.getName)
                                .consume(StreetEntityMappingDsl.$this.setHouseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberDto)
                                .from(StreetDtoMappingDsl.$this.getHouseNumber)
                                .when(houseNumberDto -> houseNumberDto.getNumber() > 100)
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

    private static final BiCondition<HouseNumberEntity, HouseNumberDto> houseNumberCondition =
            new BiCondition<HouseNumberEntity, HouseNumberDto>() {

        @Override
        public boolean testForward(HouseNumberEntity source) {
            return source.getNumber() > 100;
        }

        @Override
        public boolean testBackward(HouseNumberDto target) {
            return target.getNumber() > 100;
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
