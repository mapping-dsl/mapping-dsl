package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BiSimpleMappingTest {

    @Test
    void shouldMapSinglePrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name).with(StreetDtoMappingDsl.$this.name)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
    }

    @Test
    void shouldMapMultiplePrimitiveFields() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number).with(HouseNumberDtoMappingDsl.$this.number)
                .bind(HouseNumberEntityMappingDsl.$this.suffix).with(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        // forward mapping
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        assertThat(houseNumberEntity.getNumber()).isEqualTo(221);
        assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapMultiplePrimitiveFieldsWithDifferentDirectionality() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .supply(HouseNumberEntityMappingDsl.$this.number).to(HouseNumberDtoMappingDsl.$this.number)
                .consume(HouseNumberEntityMappingDsl.$this.suffix).from(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        // forward mapping
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isNull();

        // restore missing value to get a full object for the next mapping iteration
        houseNumberDto.setSuffix("B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        assertThat(houseNumberEntity.getNumber()).isNull();
        assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
    }

}
