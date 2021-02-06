package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.IllegalAssignmentException;
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
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MultidirectionalMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("notRestrictiveTestData")
    void shouldMapMultidirectionalWhenConfigIsNotRestrictive(String testName, MappingDsl mappingDsl) {
        // forward mapping
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(resultAddressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(resultAddressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        assertThatExceptionOfType(NoMappingException.class)
                .isThrownBy(() -> mappingDsl.map(new AddressDto(), AddressEntity.class))
                .withMessage("No mapping defined for MappingKey(" +
                        "source=class io.mappingdsl.core.tests.fixtures.AddressDto, " +
                        "target=class io.mappingdsl.core.tests.fixtures.AddressEntity)");

        HouseNumberDto houseNumberDto = new HouseNumberDto(221, "B", new Geolocation(51.523772, -0.158539));
        HouseNumberEntity resultHouseNumberDto = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);
        assertThat(resultHouseNumberDto.getNumber()).isEqualTo(221);
        assertThat(resultHouseNumberDto.getSuffix()).isEqualTo("B");
        assertThat(resultHouseNumberDto.getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(resultHouseNumberDto.getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private static Stream<Arguments> notRestrictiveTestData() {
        return Stream.of(
                Arguments.of(
                        "[non-restrictive] uni mapping uses bi mapping",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.street.name)
                                .to(AddressDtoMappingDsl.$this.street.name)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .to(AddressDtoMappingDsl.$this.houseNumber)

                                .biMapping()
                                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                                .bind(HouseNumberEntityMappingDsl.$this.number)
                                .with(HouseNumberDtoMappingDsl.$this.number)
                                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                                .with(HouseNumberDtoMappingDsl.$this.suffix)
                                .bind(HouseNumberEntityMappingDsl.$this.geolocation)
                                .with(HouseNumberDtoMappingDsl.$this.geolocation)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("restrictiveTestData")
    void shouldNotMapMultidirectionalWhenConfigIsRestrictive(String testName, MappingDsl mappingDsl) {
        // forward mapping
        StreetEntity streetEntity = new StreetEntity("Baker Street");
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539));
        AddressEntity addressEntity = new AddressEntity(streetEntity, houseNumberEntity);

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(resultAddressDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(resultAddressDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(resultAddressDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        assertThatExceptionOfType(IllegalAssignmentException.class)
                .isThrownBy(() -> mappingDsl.map(resultAddressDto, AddressEntity.class))
                .withMessage("field houseNumber of type io.mappingdsl.core.tests.fixtures.HouseNumberEntity " +
                        "cannot consume value of type io.mappingdsl.core.tests.fixtures.HouseNumberDto");
    }

    private static Stream<Arguments> restrictiveTestData() {
        return Stream.of(
                Arguments.of(
                        "[restrictive] bi mapping uses uni mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.street.name)
                                .with(AddressDtoMappingDsl.$this.street.name)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingMapping()
                                .with(AddressDtoMappingDsl.$this.houseNumber)

                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .produce(HouseNumberEntityMappingDsl.$this.geolocation)
                                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                                .build())
        );
    }

}
