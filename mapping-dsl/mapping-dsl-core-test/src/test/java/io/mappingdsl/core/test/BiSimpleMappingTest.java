package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.test.fixtures.StreetDto;
import io.mappingdsl.core.test.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.StreetEntity;
import io.mappingdsl.core.test.fixtures.StreetEntityMappingDsl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BiSimpleMappingTest {

    @Test
    void shouldMapSimpleObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name).with(StreetDtoMappingDsl.$this.name)
                .build();

        StreetEntity streetEntity = new StreetEntity("Broadway");

        // forward mapping
        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        Assertions.assertThat(streetDto.getName()).isEqualTo("Broadway");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        Assertions.assertThat(streetEntity.getName()).isEqualTo("Broadway");
    }

}
