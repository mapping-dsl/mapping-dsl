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

class UniSimpleMappingTest {

    @Test
    void shouldMapSinglePrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
    }

    @Test
    void shouldMapMultiplePrimitiveFields() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .produce(HouseNumberEntityMappingDsl.$this.number).to(HouseNumberDtoMappingDsl.$this.number)
                .produce(HouseNumberEntityMappingDsl.$this.suffix).to(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");
    }

}
