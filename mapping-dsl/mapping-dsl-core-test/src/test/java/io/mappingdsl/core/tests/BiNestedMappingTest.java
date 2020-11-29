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
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        // forward mapping
        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        if (testFlow.isForwardMapped()) {
            assertThat(streetDto.getName()).isEqualTo("Baker Street");
            assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(streetDto).isNull();
        }

        // refresh test entity for backward mapping
        streetDto = new StreetDto("Baker Street");
        streetDto.setHouseNumber(new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539)));

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        if (testFlow.isBackwardMapped()) {
            assertThat(streetEntity.getName()).isEqualTo("Baker Street");
            assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
            assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
            assertThat(streetEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
            assertThat(streetEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
        }
        else {
            assertThat(streetEntity).isNull();
        }
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[bi] flat mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.number)
                                .with(StreetDtoMappingDsl.$this.houseNumber.number)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.suffix)
                                .with(StreetDtoMappingDsl.$this.houseNumber.suffix)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                                .with(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                                .with(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] flat mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.numberProperty)
                                .with(StreetDtoMappingDsl.$this.houseNumber.numberProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.suffixProperty)
                                .with(StreetDtoMappingDsl.$this.houseNumber.suffixProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .with(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .with(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[forward] flat mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] flat mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[forward] flat mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
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
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] flat mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.name)
                                .from(StreetDtoMappingDsl.$this.name)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.number)
                                .from(StreetDtoMappingDsl.$this.houseNumber.number)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.suffix)
                                .from(StreetDtoMappingDsl.$this.houseNumber.suffix)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                                .from(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                                .from(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] flat mapping over properties",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.nameProperty)
                                .from(StreetDtoMappingDsl.$this.nameProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.numberProperty)
                                .from(StreetDtoMappingDsl.$this.houseNumber.numberProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.suffixProperty)
                                .from(StreetDtoMappingDsl.$this.houseNumber.suffixProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .from(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitudeProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .from(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitudeProperty)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[backward] flat mapping over methods",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.setName)
                                .from(StreetDtoMappingDsl.$this.getName)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.setNumber)
                                .from(StreetDtoMappingDsl.$this.houseNumber.getNumber)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.setSuffix)
                                .from(StreetDtoMappingDsl.$this.houseNumber.getSuffix)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.geolocation.setLongitude)
                                .from(StreetDtoMappingDsl.$this.houseNumber.geolocation.getLongitude)
                                .consume(StreetEntityMappingDsl.$this.houseNumber.geolocation.setLatitude)
                                .from(StreetDtoMappingDsl.$this.houseNumber.geolocation.getLatitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()),

                Arguments.of(
                        "[bi] layered mapping over fields",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .with(StreetDtoMappingDsl.$this.houseNumber)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.nameProperty)
                                .with(StreetDtoMappingDsl.$this.nameProperty)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.name)
                                .to(StreetDtoMappingDsl.$this.name)
                                .produce(StreetEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .to(StreetDtoMappingDsl.$this.houseNumber)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.nameProperty)
                                .to(StreetDtoMappingDsl.$this.nameProperty)
                                .produce(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .to(StreetDtoMappingDsl.$this.houseNumberProperty)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .produce(StreetEntityMappingDsl.$this.getName)
                                .to(StreetDtoMappingDsl.$this.setName)
                                .produce(StreetEntityMappingDsl.$this.getHouseNumber)
                                .usingMapping()
                                .to(StreetDtoMappingDsl.$this.setHouseNumber)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.name)
                                .from(StreetDtoMappingDsl.$this.name)
                                .consume(StreetEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .from(StreetDtoMappingDsl.$this.houseNumber)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.nameProperty)
                                .from(StreetDtoMappingDsl.$this.nameProperty)
                                .consume(StreetEntityMappingDsl.$this.houseNumberProperty)
                                .usingMapping()
                                .from(StreetDtoMappingDsl.$this.houseNumberProperty)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .consume(StreetEntityMappingDsl.$this.setName)
                                .from(StreetDtoMappingDsl.$this.getName)
                                .consume(StreetEntityMappingDsl.$this.setHouseNumber)
                                .usingMapping()
                                .from(StreetDtoMappingDsl.$this.getHouseNumber)

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
                                .between(StreetEntity.class).and(StreetDto.class)
                                .bind(StreetEntityMappingDsl.$this.name)
                                .with(StreetDtoMappingDsl.$this.name)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty.number)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty.number)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty.suffix)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty.suffix)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty.geolocationProperty.longitude)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty.geolocationProperty.longitude)
                                .bind(StreetEntityMappingDsl.$this.houseNumberProperty.geolocationProperty.latitude)
                                .with(StreetDtoMappingDsl.$this.houseNumberProperty.geolocationProperty.latitude)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build())
                );
    }

}
