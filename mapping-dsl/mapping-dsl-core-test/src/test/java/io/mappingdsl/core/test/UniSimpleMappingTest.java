package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.test.fixtures.StreetDto;
import io.mappingdsl.core.test.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.StreetEntity;
import io.mappingdsl.core.test.fixtures.StreetEntityMappingDsl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UniSimpleMappingTest {

    @Test
    void shouldMapSimpleObject() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        StreetEntity streetEntity = new StreetEntity("Broadway");
        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        Assertions.assertThat(streetDto.getName()).isEqualTo("Broadway");
    }

}
