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

class BiNestedMappingTest {

    @Test
    void shouldMapNestedFieldsViaFlatConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .bind(StreetEntityMappingDsl.$this.houseNumber.number)
                .with(StreetDtoMappingDsl.$this.houseNumber.number)
                .bind(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .with(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
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
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapNestedFieldsViaFlatConfigWithDifferentDirectionality() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .consume(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .from(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isNull();

        // restore missing value to get a full object for the next mapping iteration
        streetDto.getHouseNumber().setSuffix("B");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isNull();
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
    }

}
