package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    void shouldMapSingleConvertedPrimitiveFieldViaProperties() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .bind(ZipEntityMappingDsl.$this.codeProperty)
                .usingConverters(String::valueOf, Integer::valueOf)
                .with(ZipDtoMappingDsl.$this.codeProperty)
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
                .produce(ZipEntityMappingDsl.$this.code)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.code)
                .build();

        // forward mapping
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);

        assertThat(zipEntity).isNull();
    }

    @Test
    void shouldForwardMapSingleConvertedPrimitiveFieldViaMethods() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .produce(ZipEntityMappingDsl.$this.getCode)
                .usingConverter(String::valueOf)
                .to(ZipDtoMappingDsl.$this.setCode)
                .build();

        // forward mapping
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        assertThat(zipDto.getCode()).isEqualTo("123456");

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(zipDto, ZipEntity.class);

        assertThat(zipEntity).isNull();
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
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        assertThat(zipDto).isNull();

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);

        assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

    @Test
    void shouldBackwardMapSingleConvertedPrimitiveFieldViaMethods() {
        // needs to be defined explicitly for forward types propagation
        // otherwise `from` DSL step will not be able to define the target expression type
        Converter<String, Integer> stringToInteger = Integer::new;

        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(ZipEntity.class).and(ZipDto.class)
                .consume(ZipEntityMappingDsl.$this.setCode)
                .usingConverter(stringToInteger)
                .from(ZipDtoMappingDsl.$this.getCode)
                .build();

        // forward mapping
        ZipDto zipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);

        assertThat(zipDto).isNull();

        // backward mapping
        ZipEntity zipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);

        assertThat(zipEntity.getCode()).isEqualTo(123456);
    }

}
