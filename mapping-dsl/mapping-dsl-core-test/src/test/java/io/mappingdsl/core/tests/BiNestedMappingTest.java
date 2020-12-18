package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NoMappingException;
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
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.utils.BiMappingTestFlow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiNestedMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    void shouldMapNestedFields(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        // forward mapping
        AddressDto addressDto;

        try {
            addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        }
        catch (NoMappingException ignore) {
            addressDto = null;
        }

        if (testFlow.isForwardMapped()) {
            assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");
            assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(addressDto.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(addressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(addressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(addressDto).isNull();
        }

        // refresh test entity for backward mapping
        StreetDto streetDto = new StreetDto("Baker Street");
        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539));
        addressDto = new AddressDto(streetDto, houseNumberDto);

        // backward mapping
        try {
            addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        }
        catch (NoMappingException ignore) {
            addressEntity = null;
        }

        if (testFlow.isBackwardMapped()) {
            assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");
            assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(addressEntity.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(addressEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(addressEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(addressEntity).isNull();
        }
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[bi] flat mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.name)
                                .with(AddressDtoMappingDsl.$this.street.name)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.number)
                                .with(AddressDtoMappingDsl.$this.houseNumber.number)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.suffix)
                                .with(AddressDtoMappingDsl.$this.houseNumber.suffix)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                                .with(AddressDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                                .with(AddressDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] flat mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.nameProperty)
                                .with(AddressDtoMappingDsl.$this.street.nameProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.numberProperty)
                                .with(AddressDtoMappingDsl.$this.houseNumber.numberProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.suffixProperty)
                                .with(AddressDtoMappingDsl.$this.houseNumber.suffixProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .with(AddressDtoMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .with(AddressDtoMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] flat mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] flat mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] flat mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] flat mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.name)
                                .from(AddressDtoMappingDsl.$this.street.name)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.number)
                                .from(AddressDtoMappingDsl.$this.houseNumber.number)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.suffix)
                                .from(AddressDtoMappingDsl.$this.houseNumber.suffix)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                                .from(AddressDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                                .from(AddressDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] flat mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.nameProperty)
                                .from(AddressDtoMappingDsl.$this.street.nameProperty)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.numberProperty)
                                .from(AddressDtoMappingDsl.$this.houseNumber.numberProperty)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.suffixProperty)
                                .from(AddressDtoMappingDsl.$this.houseNumber.suffixProperty)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .from(AddressDtoMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .from(AddressDtoMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] flat mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.setName)
                                .from(AddressDtoMappingDsl.$this.street.getName)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.setNumber)
                                .from(AddressDtoMappingDsl.$this.houseNumber.getNumber)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.setSuffix)
                                .from(AddressDtoMappingDsl.$this.houseNumber.getSuffix)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.geolocation.setLongitude)
                                .from(AddressDtoMappingDsl.$this.houseNumber.geolocation.getLongitude)
                                .consume(AddressEntityMappingDsl.$this.houseNumber.geolocation.setLatitude)
                                .from(AddressDtoMappingDsl.$this.houseNumber.geolocation.getLatitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] layered mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.name)
                                .with(AddressDtoMappingDsl.$this.street.name)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .with(AddressDtoMappingDsl.$this.houseNumber)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.number)
                                .with(HouseNumberDtoMappingDsl.$this.number)
                                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                                .with(HouseNumberDtoMappingDsl.$this.suffix)
                                .bind(HouseNumberEntityMappingDsl.$this.geolocation)
                                .asIs()
                                .with(HouseNumberDtoMappingDsl.$this.geolocation)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] layered mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.nameProperty)
                                .with(AddressDtoMappingDsl.$this.street.nameProperty)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .with(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .bind(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .with(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .bind(HouseNumberEntityMappingDsl.$this.geolocationProperty)
                                .asIs()
                                .with(HouseNumberDtoMappingDsl.$this.geolocationProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] layered mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.houseNumber)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .produce(HouseNumberEntityMappingDsl.$this.geolocation)
                                .asIs()
                                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] layered mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.nameProperty)
                                .to(AddressDtoMappingDsl.$this.street.nameProperty)
                                .produce(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.houseNumberProperty)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .to(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .produce(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .to(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .produce(HouseNumberEntityMappingDsl.$this.geolocationProperty)
                                .asIs()
                                .to(HouseNumberDtoMappingDsl.$this.geolocationProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] layered mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.getName)
                                .to(AddressDtoMappingDsl.$this.street.setName)
                                .produce(AddressEntityMappingDsl.$this.getHouseNumber)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.setHouseNumber)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.getNumber)
                                .to(HouseNumberDtoMappingDsl.$this.setNumber)
                                .produce(HouseNumberEntityMappingDsl.$this.getSuffix)
                                .to(HouseNumberDtoMappingDsl.$this.setSuffix)
                                .produce(HouseNumberEntityMappingDsl.$this.getGeolocation)
                                .asIs()
                                .to(HouseNumberDtoMappingDsl.$this.setGeolocation)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] layered mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.name)
                                .from(AddressDtoMappingDsl.$this.street.name)
                                .consume(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .from(AddressDtoMappingDsl.$this.houseNumber)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.number)
                                .from(HouseNumberDtoMappingDsl.$this.number)
                                .consume(HouseNumberEntityMappingDsl.$this.suffix)
                                .from(HouseNumberDtoMappingDsl.$this.suffix)
                                .consume(HouseNumberEntityMappingDsl.$this.geolocation)
                                .asIs()
                                .from(HouseNumberDtoMappingDsl.$this.geolocation)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] layered mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.nameProperty)
                                .from(AddressDtoMappingDsl.$this.street.nameProperty)
                                .consume(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .from(AddressDtoMappingDsl.$this.houseNumberProperty)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .from(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .consume(HouseNumberEntityMappingDsl.$this.suffixProperty)
                                .from(HouseNumberDtoMappingDsl.$this.suffixProperty)
                                .consume(HouseNumberEntityMappingDsl.$this.geolocationProperty)
                                .asIs()
                                .from(HouseNumberDtoMappingDsl.$this.geolocationProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] layered mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.street.setName)
                                .from(AddressDtoMappingDsl.$this.street.getName)
                                .consume(AddressEntityMappingDsl.$this.setHouseNumber)
                                .usingMapping()
                                .from(AddressDtoMappingDsl.$this.getHouseNumber)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .consume(HouseNumberEntityMappingDsl.$this.setNumber)
                                .from(HouseNumberDtoMappingDsl.$this.getNumber)
                                .consume(HouseNumberEntityMappingDsl.$this.setSuffix)
                                .from(HouseNumberDtoMappingDsl.$this.getSuffix)
                                .consume(HouseNumberEntityMappingDsl.$this.setGeolocation)
                                .asIs()
                                .from(HouseNumberDtoMappingDsl.$this.getGeolocation)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] flat mapping with intermediate properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.name)
                                .with(AddressDtoMappingDsl.$this.street.name)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty.number)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty.number)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty.suffix)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty.suffix)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty.geolocationProperty.longitude)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty.geolocationProperty.longitude)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty.geolocationProperty.latitude)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty.geolocationProperty.latitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build())
                );
    }

}
