package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.Geolocation;
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

class UniNestedMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    void shouldMapNestedFields(String testName, MappingDsl mappingDsl) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[uni] flat mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.number)
                                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.suffix)
                                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                                .build()),

                Arguments.of(
                        "[uni] flat mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.nameProperty)
                                .to(StreetDtoMappingDsl.$this.nameProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.numberProperty)
                                .to(StreetDtoMappingDsl.$this.houseNumber.numberProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.suffixProperty)
                                .to(StreetDtoMappingDsl.$this.houseNumber.suffixProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .build()),

                Arguments.of(
                        "[uni] flat mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.getName)
                                .to(StreetDtoMappingDsl.$this.setName)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.getNumber)
                                .to(StreetDtoMappingDsl.$this.houseNumber.setNumber)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.getSuffix)
                                .to(StreetDtoMappingDsl.$this.houseNumber.setSuffix)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.getLongitude)
                                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.setLongitude)
                                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.getLatitude)
                                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.setLatitude)
                                .build()),

                Arguments.of(
                        "[uni] dedicated mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .to(StreetDtoMappingDsl.$this.houseNumber)

                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .produce(HouseNumberEntityMappingDsl.$this.geolocation)
                                .asIs()
                                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                                .build()),

                Arguments.of(
                        "[uni] dedicated mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .to(StreetDtoMappingDsl.$this.houseNumberProperty)

                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .produce(HouseNumberEntityMappingDsl.$this.geolocationProperty)
                                .asIs()
                                .to(HouseNumberDtoMappingDsl.$this.geolocationProperty)
                                .build()),

                Arguments.of(
                        "[uni] dedicated mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(StreetEntity.class).to(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.getHouseNumber)
                                .usingMapping()
                                .to(StreetDtoMappingDsl.$this.setHouseNumber)

                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .produce(HouseNumberEntityMappingDsl.$this.getGeolocation)
                                .asIs()
                                .to(HouseNumberDtoMappingDsl.$this.setGeolocation)
                                .build())
        );
    }

}
