package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import io.mappingdsl.core.tests.utils.BiMappingTestFlow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiSimpleMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("singleFiledTestData")
    void shouldMapSinglePrimitiveField(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");

        // forward mapping
        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        if (testFlow.isForwardMapped()) {
            assertThat(streetDto.getName()).isEqualTo("Baker Street");
        }
        else {
            assertThat(streetDto).isNull();
        }

        // refresh test entity for backward mapping
        streetDto = new StreetDto("Baker Street");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        if (testFlow.isBackwardMapped()) {
            assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        }
        else {
            assertThat(streetEntity).isNull();
        }
    }

    private static Stream<Arguments> singleFiledTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.name)
                                .from(StreetDtoMappingDsl.$this.name)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.nameProperty)
                                .to(StreetDtoMappingDsl.$this.nameProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.nameProperty)
                                .from(StreetDtoMappingDsl.$this.nameProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("multiFiledTestData")
    void shouldMapMultiplePrimitiveFields(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        // forward mapping
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        if (testFlow.isForwardMapped()) {
            assertThat(houseNumberDto.getNumber()).isEqualTo(221);
            assertThat(houseNumberDto.getSuffix()).isEqualTo("B");
        }
        else {
            assertThat(houseNumberDto).isNull();
        }

        // refresh test entity for backward mapping
        houseNumberDto = new HouseNumberDto(221, "B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        if (testFlow.isBackwardMapped()) {
            assertThat(houseNumberEntity.getNumber()).isEqualTo(221);
            assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
        }
        else {
            assertThat(houseNumberEntity).isNull();
        }
    }

    private static Stream<Arguments> multiFiledTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.number)
                                .with(HouseNumberDtoMappingDsl.$this.number)
                                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                                .with(HouseNumberDtoMappingDsl.$this.suffix)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .with(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .bind(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .with(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .to(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .produce(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .to(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.getNumber)
                                .to(HouseNumberDtoMappingDsl.$this.setNumber)
                                .produce(HouseNumberEntityMappingDsl.$this.getSuffix)
                                .to(HouseNumberDtoMappingDsl.$this.setSuffix)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.number)
                                .from(HouseNumberDtoMappingDsl.$this.number)
                                .consume(HouseNumberEntityMappingDsl.$this.suffix)
                                .from(HouseNumberDtoMappingDsl.$this.suffix)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .from(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .consume(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .from(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.setNumber)
                                .from(HouseNumberDtoMappingDsl.$this.getNumber)
                                .consume(HouseNumberEntityMappingDsl.$this.setSuffix)
                                .from(HouseNumberDtoMappingDsl.$this.getSuffix)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build())
        );
    }

}
