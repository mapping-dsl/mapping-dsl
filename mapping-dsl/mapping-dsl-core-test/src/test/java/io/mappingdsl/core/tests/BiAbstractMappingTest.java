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
import io.mappingdsl.core.tests.fixtures.SettlementEntity;
import io.mappingdsl.core.tests.utils.TestConverters;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiAbstractMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("abstractFieldsTestData")
    void shouldMapPrimitiveAbstractsField(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setPopulation(56_286_961);
        countryEntity.setNationalLanguages(new CharSequence[]{ "English" });

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getPopulation().longValue()).isEqualTo(56_286_961);
        assertThat(resultCountryDto.getNationalLanguages()).hasSize(1);
        assertThat(resultCountryDto.getNationalLanguages()[0]).isEqualTo("English");

        CountryEntity resultCountryEntity = mappingDsl.map(resultCountryDto, CountryEntity.class);

        assertThat(resultCountryEntity.getPopulation().longValue()).isEqualTo(56_286_961);
        assertThat(resultCountryEntity.getNationalLanguages()).hasSize(1);
        assertThat(resultCountryEntity.getNationalLanguages()[0]).isEqualTo("English");
    }

    private static Stream<Arguments> abstractFieldsTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.population)
                                .with(CountryDtoMappingDsl.$this.population)
                                .bind(CountryEntityMappingDsl.$this.nationalLanguages)
                                .with(CountryDtoMappingDsl.$this.nationalLanguages)
                                .build()),

                Arguments.of(
                        "[bi] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.populationProperty)
                                .with(CountryDtoMappingDsl.$this.populationProperty)
                                .bind(CountryEntityMappingDsl.$this.nationalLanguagesProperty)
                                .with(CountryDtoMappingDsl.$this.nationalLanguagesProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("abstractFieldsForwardTestData")
    void shouldForwardMapPrimitiveAbstractsField(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setPopulation(56_286_961);
        countryEntity.setNationalLanguages(new CharSequence[]{ "English" });

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getPopulation().longValue()).isEqualTo(56_286_961);
        assertThat(resultCountryDto.getNationalLanguages()).hasSize(1);
        assertThat(resultCountryDto.getNationalLanguages()[0]).isEqualTo("English");
    }

    private static Stream<Arguments> abstractFieldsForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.population)
                                .to(CountryDtoMappingDsl.$this.population)
                                .produce(CountryEntityMappingDsl.$this.nationalLanguages)
                                .to(CountryDtoMappingDsl.$this.nationalLanguages)
                                .build()),

                Arguments.of(
                        "[forward] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.populationProperty)
                                .to(CountryDtoMappingDsl.$this.populationProperty)
                                .produce(CountryEntityMappingDsl.$this.nationalLanguagesProperty)
                                .to(CountryDtoMappingDsl.$this.nationalLanguagesProperty)
                                .build()),

                Arguments.of(
                        "[forward] mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getPopulation)
                                .to(CountryDtoMappingDsl.$this.setPopulation)
                                .produce(CountryEntityMappingDsl.$this.getNationalLanguages)
                                .to(CountryDtoMappingDsl.$this.setNationalLanguages)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("abstractFieldsBackwardTestData")
    void shouldBackwardMapPrimitiveAbstractsField(String testName, MappingDsl mappingDsl) {
        CountryDto countryDto = new CountryDto();
        countryDto.setPopulation(56_286_961);
        countryDto.setNationalLanguages(new CharSequence[]{ "English" });

        CountryEntity resultCountryEntity = mappingDsl.map(countryDto, CountryEntity.class);

        assertThat(resultCountryEntity.getPopulation().longValue()).isEqualTo(56_286_961);
        assertThat(resultCountryEntity.getNationalLanguages()).hasSize(1);
        assertThat(resultCountryEntity.getNationalLanguages()[0]).isEqualTo("English");
    }

    private static Stream<Arguments> abstractFieldsBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.population)
                                .from(CountryDtoMappingDsl.$this.population)
                                .consume(CountryEntityMappingDsl.$this.nationalLanguages)
                                .from(CountryDtoMappingDsl.$this.nationalLanguages)
                                .build()),

                Arguments.of(
                        "[backward] mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.populationProperty)
                                .from(CountryDtoMappingDsl.$this.populationProperty)
                                .consume(CountryEntityMappingDsl.$this.nationalLanguagesProperty)
                                .from(CountryDtoMappingDsl.$this.nationalLanguagesProperty)
                                .build()),

                Arguments.of(
                        "[backward] mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.setPopulation)
                                .from(CountryDtoMappingDsl.$this.getPopulation)
                                .consume(CountryEntityMappingDsl.$this.setNationalLanguages)
                                .from(CountryDtoMappingDsl.$this.getNationalLanguages)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexAbstractValueTestData")
    void shouldMapComplexAbstractValue(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCapital(new CityEntity("London"));

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getCapital().getName()).isEqualTo("London");

        CountryEntity resultCountryEntity = mappingDsl.map(resultCountryDto, CountryEntity.class);

        assertThat(resultCountryEntity.getCapital().getName()).isEqualTo("London");
    }

    private static Stream<Arguments> complexAbstractValueTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] no hint, mapping over converter using fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.capital)
                                .usingConverters(TestConverters::convertCityEntity, TestConverters::convertCityDto)
                                .with(CountryDtoMappingDsl.$this.capital)
                                .build()),

                Arguments.of(
                        "[bi] no hint, mapping over converter using properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingConverters(TestConverters::convertCityEntity, TestConverters::convertCityDto)
                                .with(CountryDtoMappingDsl.$this.capitalProperty)
                                .build()),

                Arguments.of(
                        "[bi] local hint, mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.capital)
                                .usingHint(CityEntity.class)
                                .with(CountryDtoMappingDsl.$this.capital)
                                .usingHint(CityDto.class)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .bind(CityEntityMappingDsl.$this.name)
                                .with(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[bi] local hint, mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingHint(CityEntity.class)
                                .with(CountryDtoMappingDsl.$this.capitalProperty)
                                .usingHint(CityDto.class)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .bind(CityEntityMappingDsl.$this.nameProperty)
                                .with(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[bi] global hint, mapping over fields",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .onAbstract(SettlementEntity.class).useHint(CityEntity.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.capital)
                                .usingGlobalHint()
                                .with(CountryDtoMappingDsl.$this.capital)
                                .usingGlobalHint()

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .bind(CityEntityMappingDsl.$this.name)
                                .with(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[bi] global hint, mapping over properties",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .onAbstract(SettlementEntity.class).useHint(CityEntity.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .bind(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingGlobalHint()
                                .with(CountryDtoMappingDsl.$this.capitalProperty)
                                .usingGlobalHint()

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .bind(CityEntityMappingDsl.$this.nameProperty)
                                .with(CityDtoMappingDsl.$this.nameProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexAbstractValueForwardTestData")
    void shouldForwardMapComplexAbstractValue(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCapital(new CityEntity("London"));

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getCapital().getName()).isEqualTo("London");
    }

    private static Stream<Arguments> complexAbstractValueForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] no hint, mapping over converter using fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capital)
                                .usingConverter(TestConverters::convertCityEntity)
                                .to(CountryDtoMappingDsl.$this.capital)
                                .build()),

                Arguments.of(
                        "[forward] no hint, mapping over converter using properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingConverter(TestConverters::convertCityEntity)
                                .to(CountryDtoMappingDsl.$this.capitalProperty)
                                .build()),

                Arguments.of(
                        "[forward] no hint, mapping over converter using methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getCapital)
                                .usingConverter(TestConverters::convertCityEntity)
                                .to(CountryDtoMappingDsl.$this.setCapital)
                                .build()),

                Arguments.of(
                        "[forward] local hint, mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capital)
                                .usingHint(CityDto.class)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.name)
                                .to(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[forward] local hint, mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capitalProperty)
                                .usingHint(CityDto.class)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.nameProperty)
                                .to(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[forward] local hint, mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getCapital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.setCapital)
                                .usingHint(CityDto.class)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getName)
                                .to(CityDtoMappingDsl.$this.setName)
                                .build()),

                Arguments.of(
                        "[forward] global hint, mapping over fields",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capital)
                                .usingGlobalHint()

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.name)
                                .to(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[forward] global hint, mapping over properties",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.capitalProperty)
                                .usingGlobalHint()

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.nameProperty)
                                .to(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[forward] global hint, mapping over methods",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getCapital)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.setCapital)
                                .usingGlobalHint()

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getName)
                                .to(CityDtoMappingDsl.$this.setName)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexAbstractValueBackwardTestData")
    void shouldBackwardMapComplexAbstractValue(String testName, MappingDsl mappingDsl) {
        CountryDto countryDto = new CountryDto();
        countryDto.setCapital(new CityDto("London"));

        CountryEntity resultCountryEntity = mappingDsl.map(countryDto, CountryEntity.class);

        assertThat(resultCountryEntity.getCapital().getName()).isEqualTo("London");
    }

    private static Stream<Arguments> complexAbstractValueBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] no hint, mapping over converter using fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.capital)
                                .usingConverter(TestConverters::convertCityDto)
                                .from(CountryDtoMappingDsl.$this.capital)
                                .build()),

                Arguments.of(
                        "[backward] no hint, mapping over converter using properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingConverter(TestConverters::convertCityDto)
                                .from(CountryDtoMappingDsl.$this.capitalProperty)
                                .build()),

                Arguments.of(
                        "[backward] no hint, mapping over converter using methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.setCapital)
                                .usingConverter(TestConverters::convertCityDto)
                                .from(CountryDtoMappingDsl.$this.getCapital)
                                .build()),

                Arguments.of(
                        "[backward] local hint, mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.capital)
                                .usingHint(CityEntity.class)
                                .from(CountryDtoMappingDsl.$this.capital)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.name)
                                .from(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[backward] local hint, mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingHint(CityEntity.class)
                                .from(CountryDtoMappingDsl.$this.capitalProperty)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.nameProperty)
                                .from(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[backward] local hint, mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.setCapital)
                                .usingHint(CityEntity.class)
                                .from(CountryDtoMappingDsl.$this.getCapital)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.setName)
                                .from(CityDtoMappingDsl.$this.getName)
                                .build()),

                Arguments.of(
                        "[backward] global hint, mapping over fields",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementEntity.class).useHint(CityEntity.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.capital)
                                .usingGlobalHint()
                                .from(CountryDtoMappingDsl.$this.capital)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.name)
                                .from(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[backward] global hint, mapping over properties",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementEntity.class).useHint(CityEntity.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingGlobalHint()
                                .from(CountryDtoMappingDsl.$this.capitalProperty)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.nameProperty)
                                .from(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[backward] global hint, mapping over methods",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementEntity.class).useHint(CityEntity.class)
                                .biMapping()
                                .between(CountryEntity.class).and(CountryDto.class)
                                .consume(CountryEntityMappingDsl.$this.setCapital)
                                .usingGlobalHint()
                                .from(CountryDtoMappingDsl.$this.getCapital)

                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.setName)
                                .from(CityDtoMappingDsl.$this.getName)
                                .build())
        );
    }

}
