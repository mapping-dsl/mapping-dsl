package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.common.BiCondition;
import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.common.Converter;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.ZipDto;
import io.mappingdsl.core.tests.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.ZipEntity;
import io.mappingdsl.core.tests.fixtures.ZipEntityMappingDsl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiMixedMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterTestData")
    void shouldMapUsingSimpleConverterConditionally(String testName, MappingDsl mappingDsl) {
        // forward mapping + condition allows mapping
        ZipDto resultZipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
        assertThat(resultZipDto.getCode()).isNotBlank();

        // forward mapping + condition does not allow mapping
        resultZipDto = mappingDsl.map(new ZipEntity(123), ZipDto.class);
        assertThat(resultZipDto.getCode()).isNull();

        // backward mapping + condition allows mapping
        ZipEntity resultZipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);
        assertThat(resultZipEntity.getCode()).isPositive();

        // backward mapping + condition does not allow mapping
        resultZipEntity = mappingDsl.map(new ZipDto("123"), ZipEntity.class);
        assertThat(resultZipEntity.getCode()).isNull();
    }

    private static Stream<Arguments> simpleConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] lambda converters over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build()),

                Arguments.of(
                        "[bi] lambda converters over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverters(String::valueOf, Integer::valueOf)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.code)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .bind(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(primitiveConverter)
                                .with(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000, target -> target.length() > 5)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterForwardTestData")
    void shouldForwardMapUsingSimpleConverterConditionally(String testName, MappingDsl mappingDsl) {
        // forward mapping + condition allows mapping
        ZipDto resultZipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
        assertThat(resultZipDto.getCode()).isNotBlank();

        // forward mapping + condition does not allow mapping
        resultZipDto = mappingDsl.map(new ZipEntity(123), ZipDto.class);
        assertThat(resultZipDto.getCode()).isNull();
    }

    private static Stream<Arguments> simpleConverterForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .when(source -> source > 1000)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleConverterBackwardTestData")
    void shouldBackwardMapUsingSimpleConverterConditionally(String testName, MappingDsl mappingDsl) {
        // backward mapping + condition allows mapping
        ZipEntity resultZipEntity = mappingDsl.map(new ZipDto("123456"), ZipEntity.class);
        assertThat(resultZipEntity.getCode()).isPositive();

        // backward mapping + condition does not allow mapping
        resultZipEntity = mappingDsl.map(new ZipDto("123"), ZipEntity.class);
        assertThat(resultZipEntity.getCode()).isNull();
    }

    private static Stream<Arguments> simpleConverterBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.code)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.code)
                                .when(target -> target.length() > 5)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.codeProperty)
                                .when(target -> target.length() > 5)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(ZipEntity.class).and(ZipDto.class)
                                .consume(ZipEntityMappingDsl.$this.setCode)
                                .usingConverter((Converter<String, Integer>) Integer::new)
                                .from(ZipDtoMappingDsl.$this.getCode)
                                .when(target -> target.length() > 5)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterTestData")
    void shouldMapUsingComplexConverterConditionally(String testName, MappingDsl mappingDsl) {
        // prepare forward mapping
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221);
        AddressEntity addressEntity = new AddressEntity(null, houseNumberEntity);

        // forward mapping + condition allows mapping
        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isPositive();

        // prepare forward mapping
        houseNumberEntity = new HouseNumberEntity(-221);
        addressEntity = new AddressEntity(null, houseNumberEntity);

        // forward mapping + condition does not allow mapping
        resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getHouseNumber()).isNull();

        // prepare backward mapping
        HouseNumberDto houseNumberDto = new HouseNumberDto(221);
        resultAddressDto = new AddressDto(null, houseNumberDto);

        // backward mapping + condition allows mapping
        AddressEntity resultAddressEntity = mappingDsl.map(resultAddressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getHouseNumber().getNumber()).isPositive();

        // prepare backward mapping
        houseNumberDto = new HouseNumberDto(-221);
        resultAddressDto = new AddressDto(null, houseNumberDto);

        // backward mapping + condition does not allow mapping
        resultAddressEntity = mappingDsl.map(resultAddressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getHouseNumber()).isNull();
    }

    private static Stream<Arguments> complexConverterTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] encapsulated converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(houseNumberConverter)
                                .with(AddressDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberCondition)
                                .build()),

                Arguments.of(
                        "[bi] encapsulated converter mapping over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(houseNumberConverter)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberCondition)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverters(
                                        BiMixedMappingTest::convertHouseNumberEntity,
                                        BiMixedMappingTest::convertHouseNumberDto)
                                .with(AddressDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberCondition)
                                .build()),

                Arguments.of(
                        "[bi] lambda converter mapping over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverters(
                                        BiMixedMappingTest::convertHouseNumberEntity,
                                        BiMixedMappingTest::convertHouseNumberDto)
                                .with(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberCondition)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterForwardTestData")
    void shouldForwardMapUsingComplexConverterConditionally(String testName, MappingDsl mappingDsl) {
        // prepare forward mapping
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221);
        AddressEntity addressEntity = new AddressEntity(null, houseNumberEntity);

        // forward mapping + condition allows mapping
        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isPositive();

        // prepare forward mapping
        houseNumberEntity = new HouseNumberEntity(-221);
        addressEntity = new AddressEntity(null, houseNumberEntity);

        // forward mapping + condition does not allow mapping
        resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getHouseNumber()).isNull();
    }

    private static Stream<Arguments> complexConverterForwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[forward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 100)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 100)
                                .build()),

                Arguments.of(
                        "[forward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.setHouseNumber)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 100)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexConverterBackwardTestData")
    void shouldBackwardMapUsingComplexConverterConditionally(String testName, MappingDsl mappingDsl) {
        // prepare backward mapping
        HouseNumberDto houseNumberDto = new HouseNumberDto(221);
        AddressDto addressDto = new AddressDto(null, houseNumberDto);

        // backward mapping + condition allows mapping
        AddressEntity streetEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(streetEntity.getHouseNumber().getNumber()).isPositive();

        // prepare backward mapping
        houseNumberDto = new HouseNumberDto(-221);
        addressDto = new AddressDto(null, houseNumberDto);

        // backward mapping + condition does not allow mapping
        streetEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(streetEntity.getHouseNumber()).isNull();
    }

    private static Stream<Arguments> complexConverterBackwardTestData() {
        return Stream.of(
                Arguments.of(
                        "[backward] lambda converter over fields with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberDto)
                                .from(AddressDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberDto -> houseNumberDto.getNumber() > 100)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over properties with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberDto)
                                .from(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberDto -> houseNumberDto.getNumber() > 100)
                                .build()),

                Arguments.of(
                        "[backward] lambda converter over methods with condition",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.setHouseNumber)
                                .usingConverter(BiMixedMappingTest::convertHouseNumberDto)
                                .from(AddressDtoMappingDsl.$this.getHouseNumber)
                                .when(houseNumberDto -> houseNumberDto.getNumber() > 100)
                                .build())
        );
    }

    private static final BiConverter<Integer, String> primitiveConverter =
            new BiConverter<Integer, String>() {

        @Override
        public String convertForward(Integer source) {
            return String.valueOf(source);
        }

        @Override
        public Integer convertBackward(String target) {
            return Integer.valueOf(target);
        }

    };

    private static final BiCondition<HouseNumberEntity, HouseNumberDto> houseNumberCondition =
            new BiCondition<HouseNumberEntity, HouseNumberDto>() {

        @Override
        public boolean testForward(HouseNumberEntity source) {
            return source.getNumber() > 100;
        }

        @Override
        public boolean testBackward(HouseNumberDto target) {
            return target.getNumber() > 100;
        }
    };

    private static final BiConverter<HouseNumberEntity, HouseNumberDto> houseNumberConverter =
            new BiConverter<HouseNumberEntity, HouseNumberDto>() {

                @Override
                public HouseNumberDto convertForward(HouseNumberEntity source) {
                    return convertHouseNumberEntity(source);
                }

                @Override
                public HouseNumberEntity convertBackward(HouseNumberDto target) {
                    return convertHouseNumberDto(target);
                }

            };


    private static HouseNumberDto convertHouseNumberEntity(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

    private static HouseNumberEntity convertHouseNumberDto(HouseNumberDto houseNumberDto) {
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity();
        houseNumberEntity.setNumber(houseNumberDto.getNumber());
        houseNumberEntity.setSuffix(houseNumberDto.getSuffix());
        houseNumberEntity.setGeolocation(houseNumberDto.getGeolocation());
        return houseNumberEntity;
    }

}
