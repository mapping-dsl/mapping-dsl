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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniSimpleMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("singleFiledTestData")
    void shouldMapSinglePrimitiveField(String testName, MappingDsl mappingDsl) {
        StreetDto resultStreetDto = mappingDsl.map(new StreetEntity("Baker Street"), StreetDto.class);
        assertThat(resultStreetDto.getName()).isEqualTo("Baker Street");
    }

    private static Stream<Arguments> singleFiledTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.nameProperty)
                                .to(StreetDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.getName)
                                .to(StreetDtoMappingDsl.$this.setName)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("multiFiledTestData")
    void shouldMapMultiplePrimitiveFields(String testName, MappingDsl mappingDsl) {
        HouseNumberDto resultHouseNumberDto = mappingDsl.map(new HouseNumberEntity(221, "B"), HouseNumberDto.class);
        assertThat(resultHouseNumberDto.getNumber()).isEqualTo(221);
        assertThat(resultHouseNumberDto.getSuffix()).isEqualTo("B");
    }

    private static Stream<Arguments> multiFiledTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .to(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .produce(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .to(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.getNumber)
                                .to(HouseNumberDtoMappingDsl.$this.setNumber)
                                .produce(HouseNumberEntityMappingDsl.$this.getSuffix)
                                .to(HouseNumberDtoMappingDsl.$this.setSuffix)
                                .build())
        );
    }

}
