package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UniConverterMappingTest {

    @Test
    void shouldMapSingleConvertedPrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(ZipEntity.class).to(ZipDto.class)
                .produce(ZipEntityMappingDsl.$this.code)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.code)
                .build();

        ZipEntity zipEntity = new ZipEntity(123456);
        ZipDto zipDto = mappingDsl.map(zipEntity, ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

    @Test
    void shouldMapSingleConvertedPrimitiveFieldViaMethods() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(ZipEntity.class).to(ZipDto.class)
                .produce(ZipEntityMappingDsl.$this.getCode)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.setCode)
                .build();

        ZipEntity zipEntity = new ZipEntity(123456);
        ZipDto zipDto = mappingDsl.map(zipEntity, ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

    @Test
    void shouldMapSingleConvertedPrimitiveFieldViaProperties() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(ZipEntity.class).to(ZipDto.class)
                .produce(ZipEntityMappingDsl.$this.codeProperty)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.codeProperty)
                .build();

        ZipEntity zipEntity = new ZipEntity(123456);
        ZipDto zipDto = mappingDsl.map(zipEntity, ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");
    }

}
