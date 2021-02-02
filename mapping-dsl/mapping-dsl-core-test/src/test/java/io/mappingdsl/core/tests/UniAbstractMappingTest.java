package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.CountryDto;
import io.mappingdsl.core.tests.fixtures.CountryDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.CountryEntity;
import io.mappingdsl.core.tests.fixtures.CountryEntityMappingDsl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class UniAbstractMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("singleAbstractFiledTestData")
    void shouldMapSinglePrimitiveAbstractField(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setPopulation(56_286_961);

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        Assertions.assertThat(resultCountryDto.getPopulation().longValue()).isEqualTo(56_286_961);
    }

    private static Stream<Arguments> singleAbstractFiledTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.population)
                                .to(CountryDtoMappingDsl.$this.population)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.populationProperty)
                                .to(CountryDtoMappingDsl.$this.populationProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getPopulation)
                                .to(CountryDtoMappingDsl.$this.setPopulation)
                                .build())
        );
    }

}
