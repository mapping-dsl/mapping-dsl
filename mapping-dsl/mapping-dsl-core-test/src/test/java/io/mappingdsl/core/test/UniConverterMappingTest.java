package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.test.fixtures.ZipDto;
import io.mappingdsl.core.test.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.ZipEntity;
import io.mappingdsl.core.test.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UniConverterMappingTest {

    @Test
    void shouldMapSingleConvertedPrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(ZipEntity.class).to(ZipDto.class)
                .supply(ZipEntityMappingDsl.$this.code)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.code)
                .build();

        ZipEntity zipEntity = new ZipEntity(123456);
        ZipDto zipDto = mappingDsl.map(zipEntity, ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

}
