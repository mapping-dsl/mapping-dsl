package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NullSourceValueException;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class NullHandlingTest {

    @Test
    void shouldReturnNullByDefaultForNullRootObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        StreetDto streetDto = mappingDsl.map(null, StreetDto.class);

        assertThat(streetDto).isNull();
    }

    @Test
    void shouldReturnNullForNullRootObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().proceed()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        StreetDto streetDto = mappingDsl.map(null, StreetDto.class);

        assertThat(streetDto).isNull();
    }

    @Test
    void shouldFailForNullRootObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().terminate()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        assertThatExceptionOfType(NullSourceValueException.class)
                .isThrownBy(() -> mappingDsl.map(null, StreetDto.class))
                .withMessage("Source value is null");
    }

    @Test
    void shouldReturnNullByDefaultForNullNestedObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.houseNumber.number)
                .to(AddressDtoMappingDsl.$this.houseNumber.number)
                .produce(AddressEntityMappingDsl.$this.houseNumber.suffix)
                .to(AddressDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        AddressDto addressDto = mappingDsl.map(new AddressEntity(), AddressDto.class);

        assertThat(addressDto.getHouseNumber()).isNull();
    }

    @Test
    void shouldReturnNullForNullNestedObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().proceed()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.houseNumber.number)
                .to(AddressDtoMappingDsl.$this.houseNumber.number)
                .produce(AddressEntityMappingDsl.$this.houseNumber.suffix)
                .to(AddressDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        AddressDto addressDto = mappingDsl.map(new AddressEntity(), AddressDto.class);

        assertThat(addressDto.getHouseNumber()).isNull();
    }

    @Test
    void shouldFailForNullNestedObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().terminate()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.houseNumber.number)
                .to(AddressDtoMappingDsl.$this.houseNumber.number)
                .produce(AddressEntityMappingDsl.$this.houseNumber.suffix)
                .to(AddressDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        assertThatExceptionOfType(NullSourceValueException.class)
                .isThrownBy(() -> mappingDsl.map(new AddressEntity(), AddressDto.class))
                .withMessage("Intermediate expression 'AddressEntity -> field houseNumber " +
                        "of type io.mappingdsl.core.tests.fixtures.HouseNumberEntity' is evaluated to null");
    }

    @Test
    void shouldReturnNullForNullTerminalObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().terminate()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.houseNumber.number)
                .to(AddressDtoMappingDsl.$this.houseNumber.number)
                .produce(AddressEntityMappingDsl.$this.houseNumber.suffix)
                .to(AddressDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNumber(new HouseNumberEntity(221, null));

        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(addressDto.getHouseNumber().getSuffix()).isNull();
    }

}
