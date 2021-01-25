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
import io.mappingdsl.core.tests.fixtures.CityOverviewDto;
import io.mappingdsl.core.tests.fixtures.CityOverviewDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.DistrictDto;
import io.mappingdsl.core.tests.fixtures.DistrictDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.DistrictEntity;
import io.mappingdsl.core.tests.fixtures.DistrictEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.NamedObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniArrayMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleValuesTestData")
    void shouldMapArrayOfSimpleValues(String testName, MappingDsl mappingDsl) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setRemarks(new String[]{"friend's address", "not far from the center"});

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getRemarks()).containsExactlyInAnyOrder(
                "friend's address", "not far from the center");
    }

    private static Stream<Arguments> simpleValuesTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] array mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.remarks)
                                .to(AddressDtoMappingDsl.$this.remarks)
                                .build()),

                Arguments.of(
                        "[uni] array mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.remarksProperty)
                                .to(AddressDtoMappingDsl.$this.remarksProperty)
                                .build()),

                Arguments.of(
                        "[uni] array mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.getRemarks)
                                .to(AddressDtoMappingDsl.$this.setRemarks)
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
    }

    private static Stream<Arguments> complexValuesTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] array mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.districts)
                                .usingMapping()
                                .to(CityDtoMappingDsl.$this.districts)

                                .uniMapping()
                                .from(DistrictEntity.class).to(DistrictDto.class)
                                .produce(DistrictEntityMappingDsl.$this.name)
                                .to(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] array mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.districtsProperty)
                                .usingMapping()
                                .to(CityDtoMappingDsl.$this.districtsProperty)

                                .uniMapping()
                                .from(DistrictEntity.class).to(DistrictDto.class)
                                .produce(DistrictEntityMappingDsl.$this.name)
                                .to(DistrictDtoMappingDsl.$this.name)
                                .build()),

                Arguments.of(
                        "[uni] array mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(CityEntity.class).to(CityDto.class)
                                .produce(CityEntityMappingDsl.$this.getDistricts)
                                .usingMapping()
                                .to(CityDtoMappingDsl.$this.setDistricts)

                                .uniMapping()
                                .from(DistrictEntity.class).to(DistrictDto.class)
                                .produce(DistrictEntityMappingDsl.$this.name)
                                .to(DistrictDtoMappingDsl.$this.name)
                                .build())
        );
    }

    @Test
    void shouldMapArraySize() {
        DistrictEntity[] districtEntities = new DistrictEntity[] {
                new DistrictEntity("Marylebone"),
                new DistrictEntity("Westminster")
        };
        CityEntity cityEntity = new CityEntity(districtEntities);

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(CityEntity.class).to(CityOverviewDto.class)
                .produce(CityEntityMappingDsl.$this.districts.size())
                .to(CityOverviewDtoMappingDsl.$this.numberOfDistricts)
                .build();

        CityOverviewDto resultCityOverviewDto = mappingDsl.map(cityEntity, CityOverviewDto.class);
        assertThat(resultCityOverviewDto.getNumberOfDistricts()).isEqualTo(2);
    }

    @Test
    void shouldMapArrayElementByIndex() {
        DistrictEntity[] districtEntities = new DistrictEntity[] {
                new DistrictEntity("Marylebone"),
                new DistrictEntity("Westminster")
        };
        CityEntity cityEntity = new CityEntity(districtEntities);

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(CityEntity.class).to(CityOverviewDto.class)
                .produce(CityEntityMappingDsl.$this.districts.get(0))
                .usingMapping()
                .to(CityOverviewDtoMappingDsl.$this.centralDistrict)

                .uniMapping()
                .from(DistrictEntity.class).to(DistrictDto.class)
                .produce(DistrictEntityMappingDsl.$this.name)
                .to(DistrictDtoMappingDsl.$this.name)
                .build();

        CityOverviewDto resultCityOverviewDto = mappingDsl.map(cityEntity, CityOverviewDto.class);
        assertThat(resultCityOverviewDto.getCentralDistrict().getName()).isEqualTo("Marylebone");
    }

}
