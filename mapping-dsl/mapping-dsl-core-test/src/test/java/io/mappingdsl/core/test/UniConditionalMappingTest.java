package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.test.fixtures.HouseNumberDto;
import io.mappingdsl.core.test.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.HouseNumberEntity;
import io.mappingdsl.core.test.fixtures.HouseNumberEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UniConditionalMappingTest {

    @Test
    void shouldMapWhenConditionAllows() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .supply(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .when((source, target) -> source > 100)
                .supply(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldNotMapWhenConditionRejects() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .supply(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .when((source, target) -> source < 100)
                .supply(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isNull();
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");
    }

}
