package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiCondition;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.tests.utils.BiMappingTestFlow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiConditionalMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    void shouldMapConditionally(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
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
            assertThat(houseNumberEntity.getNumber()).isNull();
            assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
        }
        else {
            assertThat(houseNumberEntity).isNull();
        }
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
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
