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
import io.mappingdsl.core.tests.fixtures.common.timezone.ACST;
import io.mappingdsl.core.tests.fixtures.common.timezone.AEST;
import io.mappingdsl.core.tests.fixtures.common.timezone.AWST;
import io.mappingdsl.core.tests.fixtures.common.timezone.GMT;
import io.mappingdsl.core.tests.fixtures.common.timezone.TimeZone;
import io.mappingdsl.core.tests.utils.TestConverters;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniAbstractMappingTest {

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
    }

    private static Stream<Arguments> abstractFieldsTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.population)
                                .to(CountryDtoMappingDsl.$this.population)
                                .produce(CountryEntityMappingDsl.$this.nationalLanguages)
                                .to(CountryDtoMappingDsl.$this.nationalLanguages)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.populationProperty)
                                .to(CountryDtoMappingDsl.$this.populationProperty)
                                .produce(CountryEntityMappingDsl.$this.nationalLanguagesProperty)
                                .to(CountryDtoMappingDsl.$this.nationalLanguagesProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getPopulation)
                                .to(CountryDtoMappingDsl.$this.setPopulation)
                                .produce(CountryEntityMappingDsl.$this.getNationalLanguages)
                                .to(CountryDtoMappingDsl.$this.setNationalLanguages)
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
    }

    private static Stream<Arguments> complexAbstractValueTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] no hint, mapping over converter using fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capital)
                                .usingConverter(TestConverters::convertCityEntity)
                                .to(CountryDtoMappingDsl.$this.capital)
                                .build()),

                Arguments.of(
                        "[uni] no hint, mapping over converter using properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.capitalProperty)
                                .usingConverter(TestConverters::convertCityEntity)
                                .to(CountryDtoMappingDsl.$this.capitalProperty)
                                .build()),

                Arguments.of(
                        "[uni] no hint, mapping over converter using methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getCapital)
                                .usingConverter(TestConverters::convertCityEntity)
                                .to(CountryDtoMappingDsl.$this.setCapital)
                                .build()),

                Arguments.of(
                        "[uni] local hint, mapping over fields",

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
                        "[uni] local hint, mapping over properties",

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
                        "[uni] local hint, mapping over methods",

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
                                .build()),

                Arguments.of(
                        "[uni] global hint, mapping over fields",

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
                        "[uni] global hint, mapping over properties",

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
                        "[uni] global hint, mapping over methods",

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

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexAbstractCollectionTestData")
    void shouldMapComplexAbstractCollection(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        List<CityEntity> cityEntities = Collections.singletonList(new CityEntity("London"));
        countryEntity.setBiggestCities(cityEntities);

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getBiggestCities().size()).isEqualTo(1);
        assertThat(resultCountryDto.getBiggestCities().get(0).getName()).isEqualTo("London");
    }

    private static Stream<Arguments> complexAbstractCollectionTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] local hint, mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.biggestCities)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.biggestCities)
                                .usingHint(CityDto.class)

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.name)
                                .to(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] local hint, mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.biggestCitiesProperty)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.biggestCitiesProperty)
                                .usingHint(CityDto.class)

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.nameProperty)
                                .to(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[uni] local hint, mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getBiggestCities)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.setBiggestCities)
                                .usingHint(CityDto.class)

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getName)
                                .to(CityDtoMappingDsl.$this.setName)
                                .build()),

                Arguments.of(
                        "[uni] global hint, mapping over fields",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.biggestCities)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.biggestCities)
                                .usingGlobalHint()

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.name)
                                .to(CityDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] global hint, mapping over properties",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.biggestCitiesProperty)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.biggestCitiesProperty)
                                .usingGlobalHint()

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.nameProperty)
                                .to(CityDtoMappingDsl.$this.nameProperty)
                                .build()),

                Arguments.of(
                        "[uni] global hint, mapping over methods",

                        new MappingDslBuilder()
                                .configuration()
                                .onAbstract(SettlementDto.class).useHint(CityDto.class)
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getBiggestCities)
                                .usingMapping()
                                .to(CountryDtoMappingDsl.$this.setBiggestCities)
                                .usingGlobalHint()

                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getName)
                                .to(CityDtoMappingDsl.$this.setName)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexCompatibleAbstractTestData")
    void shouldMapComplexCompatibleAbstractValue(String testName, MappingDsl mappingDsl) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setTimeZone(new GMT());

        CityDto resultCityDto = mappingDsl.map(cityEntity, CityDto.class);

        assertThat(resultCityDto.getTimeZone().getUtcOffsetHours()).isZero();
        assertThat(resultCityDto.getTimeZone().getUtcOffsetMinutes()).isZero();
    }

    private static Stream<Arguments> complexCompatibleAbstractTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.timeZone)
                                .to(CityDtoMappingDsl.$this.timeZone)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.timeZoneProperty)
                                .to(CityDtoMappingDsl.$this.timeZoneProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getTimeZone)
                                .to(CityDtoMappingDsl.$this.setTimeZone)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexComplexCompatibleAbstractCollectionTestData")
    void shouldMapComplexCompatibleAbstractCollection(String testName, MappingDsl mappingDsl) {
        CountryEntity countryEntity = new CountryEntity();
        List<TimeZone> timeZones = Arrays.asList(new AEST(), new ACST(), new AWST());
        countryEntity.setTimeZones(timeZones);

        CountryDto resultCountryDto = mappingDsl.map(countryEntity, CountryDto.class);

        assertThat(resultCountryDto.getTimeZones()).containsAll(timeZones);
    }

    private static Stream<Arguments> complexComplexCompatibleAbstractCollectionTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.timeZones)
                                .to(CountryDtoMappingDsl.$this.timeZones)
                                .build()),

                Arguments.of(
                        "[uni] mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.timeZonesProperty)
                                .to(CountryDtoMappingDsl.$this.timeZonesProperty)
                                .build()),

                Arguments.of(
                        "[uni] mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CountryEntity.class).to(CountryDto.class)
                                .produce(CountryEntityMappingDsl.$this.getTimeZones)
                                .to(CountryDtoMappingDsl.$this.setTimeZones)
                                .build())
        );
    }

}
