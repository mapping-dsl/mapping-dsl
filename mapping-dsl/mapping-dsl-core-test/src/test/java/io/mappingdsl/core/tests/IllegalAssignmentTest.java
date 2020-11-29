package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.IllegalAssignmentException;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IllegalAssignmentTest {

    @Test
    void shouldFailIfAssignmentCannotBeDone() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .to(StreetDtoMappingDsl.$this.houseNumber)

                // missing mapping
                //.uniMapping()
                //.from(HouseNumberEntity.class).to(HouseNumberDto.class)
                // ...

                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        assertThatThrownBy(() -> mappingDsl.map(streetEntity, StreetDto.class))
                .isInstanceOf(IllegalAssignmentException.class)
                .hasMessage("houseNumber (field access) cannot consume value of type " +
                        "io.mappingdsl.core.tests.fixtures.HouseNumberEntity");
    }

}
