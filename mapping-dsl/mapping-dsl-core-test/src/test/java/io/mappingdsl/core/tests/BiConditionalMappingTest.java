package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiCondition;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiConditionalMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    void shouldMapConditionally(String testName, MappingDsl mappingDsl) {
        HouseNumberDto resultHouseNumberDto = mappingDsl.map(new HouseNumberEntity(221, "B"), HouseNumberDto.class);
        assertThat(resultHouseNumberDto.getNumber()).isEqualTo(221);
        assertThat(resultHouseNumberDto.getSuffix()).isEqualTo("B");

        HouseNumberEntity resultHouseNumberEntity = mappingDsl.map(resultHouseNumberDto, HouseNumberEntity.class);
        assertThat(resultHouseNumberEntity.getNumber()).isNull();
        assertThat(resultHouseNumberEntity.getSuffix()).isEqualTo("B");

    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[bi] lambda conditions over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.number)
                                .with(HouseNumberDtoMappingDsl.$this.number)
                                .when(source -> source > 100, target -> target < 100)
                                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                                .with(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[bi] lambda conditions over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .with(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .when(source -> source > 100, target -> target < 100)
                                .bind(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .with(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated conditions over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.number)
                                .with(HouseNumberDtoMappingDsl.$this.number)
                                .when(BiConditionalMappingTest.condition)
                                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                                .with(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated conditions over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .with(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .when(BiConditionalMappingTest.condition)
                                .bind(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .with(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("forwardTestData")
    void shouldForwardMapConditionally(String testName, MappingDsl mappingDsl) {
        HouseNumberDto resultHouseNumberDto = mappingDsl.map(new HouseNumberEntity(221, "B"), HouseNumberDto.class);
        assertThat(resultHouseNumberDto.getNumber()).isEqualTo(221);
        assertThat(resultHouseNumberDto.getSuffix()).isEqualTo("B");
    }

    private static Stream<Arguments> forwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] lambda condition over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[forward] lambda condition over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .to(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .to(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build()),

                Arguments.of(
                        "[forward] lambda condition over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.getNumber)
                                .to(HouseNumberDtoMappingDsl.$this.setNumber)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.getSuffix)
                                .to(HouseNumberDtoMappingDsl.$this.setSuffix)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("backwardTestData")
    void shouldBackwardMapConditionally(String testName, MappingDsl mappingDsl) {
        HouseNumberEntity resultHouseNumberEntity = mappingDsl.map(new HouseNumberDto(221, "B"), HouseNumberEntity.class);
        assertThat(resultHouseNumberEntity.getNumber()).isNull();
        assertThat(resultHouseNumberEntity.getSuffix()).isEqualTo("B");
    }

    private static Stream<Arguments> backwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] lambda condition over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.number)
                                .from(HouseNumberDtoMappingDsl.$this.number)
                                .when(target -> target < 100)
                                .consume(HouseNumberEntityMappingDsl.$this.suffix)
                                .from(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[backward] lambda condition over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .from(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .when(target -> target < 100)
                                .consume(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .from(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build()),

                Arguments.of(
                        "[backward] lambda condition over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.setNumber)
                                .from(HouseNumberDtoMappingDsl.$this.getNumber)
                                .when(target -> target < 100)
                                .consume(HouseNumberEntityMappingDsl.$this.setSuffix)
                                .from(HouseNumberDtoMappingDsl.$this.getSuffix)
                                .build())
        );
    }

    private static final BiCondition<Integer, Integer> condition = new BiCondition<Integer, Integer>() {

        @Override
        public boolean testForward(Integer source) {
            return source > 100;
        }

        @Override
        public boolean testBackward(Integer target) {
            return target < 100;
        }

    };

}
