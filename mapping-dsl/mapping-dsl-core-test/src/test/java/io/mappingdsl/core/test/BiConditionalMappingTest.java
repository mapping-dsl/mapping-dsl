package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.BiCondition;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.test.fixtures.HouseNumberDto;
import io.mappingdsl.core.test.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.HouseNumberEntity;
import io.mappingdsl.core.test.fixtures.HouseNumberEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BiConditionalMappingTest {

    @Test
    void shouldMapWhenConditionAllowsUsingSeparatePredicates() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number)
                .with(HouseNumberDtoMappingDsl.$this.number)
                .when((source, target) -> source > 100, (target, source) -> target < 100)
                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                .with(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        // forward mapping
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        assertThat(houseNumberEntity.getNumber()).isNull();
        assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapWhenConditionAllowsUsingUnifiedPredicate() {
        BiCondition<Integer, Integer> condition = new BiCondition<Integer, Integer>() {

            @Override
            public boolean testForward(Integer source, Integer target) {
                return source > 100;
            }

            @Override
            public boolean testBackward(Integer target, Integer source) {
                return target < 100;
            }
        };

        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number)
                .with(HouseNumberDtoMappingDsl.$this.number)
                .when(condition)
                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                .with(HouseNumberDtoMappingDsl.$this.suffix)
                .build();

        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");

        // forward mapping
        HouseNumberDto houseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);

        assertThat(houseNumberDto.getNumber()).isEqualTo(221);
        assertThat(houseNumberDto.getSuffix()).isEqualTo("B");

        // backward mapping
        houseNumberEntity = mappingDsl.map(houseNumberDto, HouseNumberEntity.class);

        assertThat(houseNumberEntity.getNumber()).isNull();
        assertThat(houseNumberEntity.getSuffix()).isEqualTo("B");
    }

}
