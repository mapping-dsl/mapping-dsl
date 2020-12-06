package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniConditionalMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    void shouldMapWhenConditionAllows(String testName, MappingDsl mappingDsl) {
        // passed condition
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");

        // rejected condition
        houseNumberEntity = new HouseNumberEntity(-221, "B");
        houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isNull();
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[uni] condition over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[uni] condition over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .to(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[uni] condition over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.getNumber)
                                .to(HouseNumberDtoMappingDsl.$this.setNumber)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build())
        );
    }

}
