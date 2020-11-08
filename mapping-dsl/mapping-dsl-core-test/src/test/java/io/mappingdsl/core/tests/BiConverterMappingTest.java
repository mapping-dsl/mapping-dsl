package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.BiConverter;
import io.mappingdsl.core.builder.Converter;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NoMappingException;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);

        assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

    @Test
    void shouldMapSingleConvertedPrimitiveFieldWithBiConverter() {
        BiConverter<Integer, String> converter = new BiConverter<Integer, String>() {

            @Override
            public String convertForward(Integer source) {
                return String.valueOf(source);
            }

            @Override
            public Integer convertBackward(String target) {
                return Integer.valueOf(target);
            }
        };

        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .bind(ZipEntityMappingDsl.$this.code)
                .usingConverter(converter)
                .with(ZipDtoMappingDsl.$this.code)
                .build();

        // forward mapping
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);

        assertThat(zipEntity.getCode()).isEqualTo(123456);
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

        assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        assertThatThrownBy(() -> mappingDsl.map(zipDto, ZipEntity.class))
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
        assertThatThrownBy(() -> mappingDsl.map(new ZipEntity(123456), ZipDto.class))
                .isInstanceOf(NoMappingException.class);

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);

        assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

}