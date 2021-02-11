package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.CountryDto;
import io.mappingdsl.core.tests.fixtures.CountryDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.CountryEntity;
import io.mappingdsl.core.tests.fixtures.CountryEntityMappingDsl;
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

}
