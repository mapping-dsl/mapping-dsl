package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.IllegalAssignmentException;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IllegalAssignmentTest {

    @Test
    void shouldFailIfAssignmentCannotBeDone() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .to(AddressDtoMappingDsl.$this.houseNumber)

                // missing mapping
                //.uniMapping()
                //.from(HouseNumberEntity.class).to(HouseNumberDto.class)
                // ...

                .build();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        assertThatThrownBy(() -> mappingDsl.map(addressEntity, AddressDto.class))
                .isInstanceOf(IllegalAssignmentException.class)
                .hasMessage("field houseNumber of type io.mappingdsl.core.tests.fixtures.HouseNumberDto " +
                        "cannot consume value of type io.mappingdsl.core.tests.fixtures.HouseNumberEntity. " +
                        "Probably mapping configuration or hint is missing for this pair of types.");
    }

}
