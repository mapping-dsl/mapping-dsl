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

class UniNestedMappingTest {

    @Test
    void shouldMapNestedFieldsViaFlatConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .supply(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .supply(StreetEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .to(StreetDtoMappingDsl.$this.houseNumber)

                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .supply(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .supply(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
    }

}
