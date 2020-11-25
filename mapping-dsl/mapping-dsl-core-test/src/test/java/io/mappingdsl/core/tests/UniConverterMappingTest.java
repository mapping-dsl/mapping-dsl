package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.Geolocation;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniConverterMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("primitiveFieldTestData")
    void shouldMapConvertedPrimitiveField(String testName, MappingDsl mappingDsl) {
        ZipEntity zipEntity = new ZipEntity(123456);
        ZipDto zipDto = mappingDsl.map(zipEntity, ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

    private static Stream<Arguments> primitiveFieldTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] converter over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .build()),

                Arguments.of(
                        "[uni] converter over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .build()),

                Arguments.of(
                        "[uni] converter over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexFieldTestData")
    void shouldMapConvertedComplexField(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> complexFieldTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] converter over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumber)
                                .usingConverter(UniConverterMappingTest::convertHouseNumber)
                                .to(StreetDtoMappingDsl.$this.houseNumber)
                                .build()),

                Arguments.of(
                        "[uni] converter over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(UniConverterMappingTest::convertHouseNumber)
                                .to(StreetDtoMappingDsl.$this.houseNumberProperty)
                                .build()),

                Arguments.of(
                        "[uni] converter over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(UniConverterMappingTest::convertHouseNumber)
                                .to(StreetDtoMappingDsl.$this.setHouseNumber)
                                .build())
        );
    }

    private static HouseNumberDto convertHouseNumber(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

}
