package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.Converter;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NoMappingException;
import io.mappingdsl.core.test.fixtures.ZipDto;
import io.mappingdsl.core.test.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.ZipEntity;
import io.mappingdsl.core.test.fixtures.ZipEntityMappingDsl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BiConverterMappingTest {

    @Test
    void shouldMapSingleConvertedPrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .bind(ZipEntityMappingDsl.$this.code)
                .usingConverters(String::valueOf, Integer::valueOf)
                .with(ZipDtoMappingDsl.$this.code)
                .build();

        // forward mapping
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        Assertions.assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);

        Assertions.assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

    @Test
    void shouldForwardMapSingleConvertedPrimitiveField() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .supply(ZipEntityMappingDsl.$this.code)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.code)
                .build();

        // forward mapping
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        Assertions.assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        Assertions.assertThatThrownBy(() -> mappingDsl.map(zipDto, ZipEntity.class))
                .isInstanceOf(NoMappingException.class);
    }

    @Test
    void shouldBackwardMapSingleConvertedPrimitiveField() {
        // needs to be defined explicitly for forward types propagation
        // otherwise `from` DSL step will not be able to define the target expression type
        Converter<String, Integer> stringToInteger = Integer::new;

        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .consume(ZipEntityMappingDsl.$this.code)
                .usingConverter(stringToInteger)
                .from(ZipDtoMappingDsl.$this.code)
                .build();

        // forward mapping
        Assertions.assertThatThrownBy(() -> mappingDsl.map(new ZipEntity(123456), ZipDto.class))
                .isInstanceOf(NoMappingException.class);

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);

        Assertions.assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

}
