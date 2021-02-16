package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.CityDto;
import io.mappingdsl.core.tests.fixtures.CityDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.CityEntity;
import io.mappingdsl.core.tests.fixtures.CityEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.DistrictDto;
import io.mappingdsl.core.tests.fixtures.DistrictDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.DistrictEntity;
import io.mappingdsl.core.tests.fixtures.DistrictEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.common.NamedObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiArrayMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleValuesTestData")
    void shouldMapArrayOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setRemarks(new String[]{"friend's address", "not far from the center"});

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getRemarks()).containsExactlyInAnyOrder(
                "friend's address", "not far from the center");

        AddressEntity resultAddressEntity = mappingDsl.map(resultAddressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getRemarks()).containsExactlyInAnyOrder(
                "friend's address", "not far from the center");
    }

    private static Stream<Arguments> simpleValuesTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] array mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.remarks)
                                .with(AddressDtoMappingDsl.$this.remarks)
                                .build()),

                Arguments.of(
                        "[bi] array mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.remarksProperty)
                                .with(AddressDtoMappingDsl.$this.remarksProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleValuesForwardTestData")
    void shouldForwardMapArrayOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setRemarks(new String[]{"friend's address", "not far from the center"});

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getRemarks()).containsExactlyInAnyOrder(
                "friend's address", "not far from the center");
    }

    private static Stream<Arguments> simpleValuesForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] array mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.remarks)
                                .to(AddressDtoMappingDsl.$this.remarks)
                                .build()),

                Arguments.of(
                        "[forward] array mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.remarksProperty)
                                .to(AddressDtoMappingDsl.$this.remarksProperty)
                                .build()),

                Arguments.of(
                        "[forward] array mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.getRemarks)
                                .to(AddressDtoMappingDsl.$this.setRemarks)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleValuesBackwardTestData")
    void shouldBackwardMapArrayOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressDto addressDto = new AddressDto();
        addressDto.setRemarks(new String[]{"friend's address", "not far from the center"});

        AddressEntity resultAddressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getRemarks()).containsExactlyInAnyOrder(
                "friend's address", "not far from the center");
    }

    private static Stream<Arguments> simpleValuesBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] array mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.remarks)
                                .from(AddressDtoMappingDsl.$this.remarks)
                                .build()),

                Arguments.of(
                        "[backward] array mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.remarksProperty)
                                .from(AddressDtoMappingDsl.$this.remarksProperty)
                                .build()),

                Arguments.of(
                        "[backward] array mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.setRemarks)
                                .from(AddressDtoMappingDsl.$this.getRemarks)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexValuesTestData")
    void shouldMapArrayOfComplexValues(String testName, MappingDsl mappingDsl) {
        DistrictEntity[] districtEntities = new DistrictEntity[] {
                new DistrictEntity("Marylebone"),
                new DistrictEntity("Westminster")
        };
        CityEntity cityEntity = new CityEntity(districtEntities);

        CityDto resultCityDto = mappingDsl.map(cityEntity, CityDto.class);
        assertThat(resultCityDto.getDistricts()).extracting(NamedObject::getName)
                .containsExactlyInAnyOrder("Marylebone", "Westminster");

        CityEntity resultCityEntity = mappingDsl.map(resultCityDto, CityEntity.class);
        assertThat(resultCityEntity.getDistricts()).extracting(NamedObject::getName)
                .containsExactlyInAnyOrder("Marylebone", "Westminster");
    }

    private static Stream<Arguments> complexValuesTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] array mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .bind(CityEntityMappingDsl.$this.districts)
                                .usingMapping()
                                .with(CityDtoMappingDsl.$this.districts)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[bi] array mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .bind(CityEntityMappingDsl.$this.districtsProperty)
                                .usingMapping()
                                .with(CityDtoMappingDsl.$this.districtsProperty)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexValuesForwardTestData")
    void shouldForwardMapArrayOfComplexValues(String testName, MappingDsl mappingDsl) {
        DistrictEntity[] districtEntities = new DistrictEntity[] {
                new DistrictEntity("Marylebone"),
                new DistrictEntity("Westminster")
        };
        CityEntity cityEntity = new CityEntity(districtEntities);

        CityDto resultCityDto = mappingDsl.map(cityEntity, CityDto.class);
        assertThat(resultCityDto.getDistricts()).extracting(NamedObject::getName)
                .containsExactlyInAnyOrder("Marylebone", "Westminster");
    }

    private static Stream<Arguments> complexValuesForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] array mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.districts)
                                .usingMapping()
                                .to(CityDtoMappingDsl.$this.districts)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[forward] array mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.districtsProperty)
                                .usingMapping()
                                .to(CityDtoMappingDsl.$this.districtsProperty)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[forward] array mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getDistricts)
                                .usingMapping()
                                .to(CityDtoMappingDsl.$this.setDistricts)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexValuesBackwardTestData")
    void shouldBackwardMapArrayOfComplexValues(String testName, MappingDsl mappingDsl) {
        DistrictDto[] districtDtos = new DistrictDto[] {
                new DistrictDto("Marylebone"),
                new DistrictDto("Westminster")
        };
        CityDto cityDto = new CityDto(districtDtos);

        CityEntity resultCityEntity = mappingDsl.map(cityDto, CityEntity.class);
        assertThat(resultCityEntity.getDistricts()).extracting(NamedObject::getName)
                .containsExactlyInAnyOrder("Marylebone", "Westminster");
    }

    private static Stream<Arguments> complexValuesBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] array mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.districts)
                                .usingMapping()
                                .from(CityDtoMappingDsl.$this.districts)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[backward] array mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.districtsProperty)
                                .usingMapping()
                                .from(CityDtoMappingDsl.$this.districtsProperty)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[backward] array mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(CityEntity.class).and(CityDto.class)
                                .consume(CityEntityMappingDsl.$this.setDistricts)
                                .usingMapping()
                                .from(CityDtoMappingDsl.$this.getDistricts)

                                .biMapping()
                                .between(DistrictEntity.class).and(DistrictDto.class)
                                .bind(DistrictEntityMappingDsl.$this.name)
                                .with(DistrictDtoMappingDsl.$this.name)
                                .build())
        );
    }

}
