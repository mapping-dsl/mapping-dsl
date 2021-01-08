package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.Geolocation;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;
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
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(resultAddressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(resultAddressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[uni] flat mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.number)
                                .to(AddressDtoMappingDsl.$this.houseNumber.number)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.suffix)
                                .to(AddressDtoMappingDsl.$this.houseNumber.suffix)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                                .to(AddressDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                                .to(AddressDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                                .build()),

                Arguments.of(
                        "[uni] flat mapping over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.nameProperty)
                                .to(AddressDtoMappingDsl.$this.street.nameProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.numberProperty)
                                .to(AddressDtoMappingDsl.$this.houseNumber.numberProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.suffixProperty)
                                .to(AddressDtoMappingDsl.$this.houseNumber.suffixProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .to(AddressDtoMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .to(AddressDtoMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .build()),

                Arguments.of(
                        "[uni] flat mapping over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.getName)
                                .to(AddressDtoMappingDsl.$this.street.setName)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.getNumber)
                                .to(AddressDtoMappingDsl.$this.houseNumber.setNumber)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.getSuffix)
                                .to(AddressDtoMappingDsl.$this.houseNumber.setSuffix)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.geolocation.getLongitude)
                                .to(AddressDtoMappingDsl.$this.houseNumber.geolocation.setLongitude)
                                .produce(AddressEntityMappingDsl.$this.houseNumber.geolocation.getLatitude)
                                .to(AddressDtoMappingDsl.$this.houseNumber.geolocation.setLatitude)
                                .build()),

                Arguments.of(
                        "[uni] dedicated mapping over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.houseNumber)

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
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.houseNumberProperty)

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
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.getHouseNumber)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.setHouseNumber)

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

    @Test
    void shouldMapDifferentHierarchyLevels() {
        StreetEntity streetEntity = new StreetEntity("Baker Street");

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(AddressDto.class)
                .produce(StreetEntityMappingDsl.$this)
                .usingMapping()
                .to(AddressDtoMappingDsl.$this.street)

                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .build();

        StreetDto resultStreetDto = mappingDsl.map(streetEntity, StreetDto.class);
        assertThat(resultStreetDto.getName()).isEqualTo("Baker Street");
    }

}
