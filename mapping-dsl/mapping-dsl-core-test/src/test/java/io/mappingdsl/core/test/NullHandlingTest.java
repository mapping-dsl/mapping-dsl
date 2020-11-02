package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NullSourceValueException;
import io.mappingdsl.core.test.fixtures.HouseNumberEntity;
import io.mappingdsl.core.test.fixtures.StreetDto;
import io.mappingdsl.core.test.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.StreetEntity;
import io.mappingdsl.core.test.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class NullHandlingTest {

    @Test
    void shouldReturnNullByDefaultForNullRootObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
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
                .supply(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
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
                .supply(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        assertThatExceptionOfType(NullSourceValueException.class)
                .isThrownBy(() -> mappingDsl.map(null, StreetDto.class))
                .withMessage("Source value is null");
    }

    @Test
    void shouldReturnNullByDefaultForNullNestedObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .supply(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        StreetDto streetDto = mappingDsl.map(new StreetEntity(), StreetDto.class);

        assertThat(streetDto.getHouseNumber()).isNull();
    }

    @Test
    void shouldReturnNullForNullNestedObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().proceed()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .supply(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        StreetDto streetDto = mappingDsl.map(new StreetEntity(), StreetDto.class);

        assertThat(streetDto.getHouseNumber()).isNull();
    }

    @Test
    void shouldFailForNullNestedObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().terminate()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .supply(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        assertThatExceptionOfType(NullSourceValueException.class)
                .isThrownBy(() -> mappingDsl.map(new StreetEntity(), StreetDto.class))
                .withMessage("Intermediate expression 'StreetEntity -> HouseNumberEntity.houseNumber (field access)' " +
                        "is evaluated to null");
    }

    @Test
    void shouldReturnNullForNullTerminalObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onNull().terminate()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .supply(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setHouseNumber(new HouseNumberEntity(221, null));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getHouseNumber().getSuffix()).isNull();
    }

}
