package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.CityDto;
import io.mappingdsl.core.tests.fixtures.CityDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.CityEntity;
import io.mappingdsl.core.tests.fixtures.CityEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.CountryDto;
import io.mappingdsl.core.tests.fixtures.CountryDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.CountryEntity;
import io.mappingdsl.core.tests.fixtures.CountryEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.SettlementDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class UniAbstractMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("abstractFiledTestData")
    void shouldMapPrimitiveAbstractField(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setPopulation(56_286_961);

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        Assertions.assertThat(resultCountryDto.getPopulation().longValue()).isEqualTo(56_286_961);
    }

    private static Stream<Arguments> abstractFiledTestData() {
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

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexAbstractFiledWithLocalHintTestData")
    void shouldMapComplexAbstractFieldWithLocalHint(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCapital(new CityEntity("London"));

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        Assertions.assertThat(resultCountryDto.getCapital().getName()).isEqualTo("London");
    }

    private static Stream<Arguments> complexAbstractFiledWithLocalHintTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capital)
                                .usingHint(CityDto.class)

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.name)
                                .to(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capitalProperty)
                                .usingHint(CityDto.class)

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.nameProperty)
                                .to(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getCapital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.setCapital)
                                .usingHint(CityDto.class)

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getName)
                                .to(CityDtoMappingDsl.$this.setName)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexAbstractFiledWithGlobalHintTestData")
    void shouldMapComplexAbstractFieldWithGlobalHint(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCapital(new CityEntity("London"));

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        Assertions.assertThat(resultCountryDto.getCapital().getName()).isEqualTo("London");
    }

    private static Stream<Arguments> complexAbstractFiledWithGlobalHintTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capital)
                                .usingGlobalHint()

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.name)
                                .to(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capitalProperty)
                                .usingGlobalHint()

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.nameProperty)
                                .to(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getCapital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.setCapital)
                                .usingGlobalHint()

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getName)
                                .to(CityDtoMappingDsl.$this.setName)
                                .build())
        );
    }

}
