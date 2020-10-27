package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.test.fixtures.HouseNumberDto;
import io.mappingdsl.core.test.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.HouseNumberEntity;
import io.mappingdsl.core.test.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.test.fixtures.StreetDto;
import io.mappingdsl.core.test.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.StreetEntity;
import io.mappingdsl.core.test.fixtures.StreetEntityMappingDsl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BiSimpleMappingTest {

    @Test
    void shouldMapSinglePrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name).with(StreetDtoMappingDsl.$this.name)
                .build();

        // forward mapping
        StreetDto streetDto = mappingDsl.map(new StreetEntity("Baker Street"), StreetDto.class);

        Assertions.assertThat(streetDto.getName()).isEqualTo("Baker Street");

        // backward mapping
        StreetEntity streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        Assertions.assertThat(streetEntity.getName()).isEqualTo("Baker Street");
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

        Assertions.assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        Assertions.assertThat(houseNumberDto.getSuffix()).isEqualTo("B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        Assertions.assertThat(houseNumberEntity.getNumber()).isEqualTo(221);
        Assertions.assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
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

        Assertions.assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        Assertions.assertThat(houseNumberDto.getSuffix()).isNull();

        // restore missing value to get a full object for the next mapping iteration
        houseNumberDto.setSuffix("B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        Assertions.assertThat(houseNumberEntity.getNumber()).isNull();
        Assertions.assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
    }

}
