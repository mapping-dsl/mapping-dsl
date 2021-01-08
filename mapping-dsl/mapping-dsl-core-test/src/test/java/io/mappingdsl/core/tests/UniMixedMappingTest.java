package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
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
import io.mappingdsl.core.tests.utils.TestConverters;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniMixedMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("primitiveFieldTestData")
    void shouldMapConvertedPrimitiveFieldConditionally(String testName, MappingDsl mappingDsl) {
        // condition allows mapping
        ZipDto resultZipDto = mappingDsl.map(new ZipEntity(123456), ZipDto.class);
        assertThat(resultZipDto.getCode()).isNotBlank();

        // condition does not allow mapping
        resultZipDto = mappingDsl.map(new ZipEntity(123), ZipDto.class);
        assertThat(resultZipDto.getCode()).isNull();
    }

    private static Stream<Arguments> primitiveFieldTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] converter over fields with condition",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.code)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.code)
                                .when(source -> source > 1000)
                                .build()),

                Arguments.of(
                        "[uni] converter over properties with condition",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.getCode)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.setCode)
                                .when(source -> source > 1000)
                                .build()),

                Arguments.of(
                        "[uni] converter over methods with condition",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(ZipEntity.class).to(ZipDto.class)
                                .produce(ZipEntityMappingDsl.$this.codeProperty)
                                .usingConverter(String::valueOf)
                                .to(ZipDtoMappingDsl.$this.codeProperty)
                                .when(source -> source > 1000)
                                .build())
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("complexFieldTestData")
    void shouldMapConvertedComplexFieldConditionally(String testName, MappingDsl mappingDsl) {
        // condition allows mapping
        AddressEntity addressEntity = new AddressEntity();
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221);
        addressEntity.setHouseNumber(houseNumberEntity);

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getHouseNumber()).isNotNull();

        // condition does not allow mapping
        addressEntity.setHouseNumber(new HouseNumberEntity(-221));

        resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getHouseNumber()).isNull();
    }

    private static Stream<Arguments> complexFieldTestData() {
        return Stream.of(
                Arguments.of(
                        "[uni] converter over fields with condition",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.houseNumber)
                                .usingConverter(TestConverters::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.houseNumber)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 0)
                                .build()),

                Arguments.of(
                        "[uni] converter over properties with condition",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.houseNumberProperty)
                                .usingConverter(TestConverters::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.houseNumberProperty)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 0)
                                .build()),

                Arguments.of(
                        "[uni] converter over methods with condition",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(AddressEntity.class).to(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.getHouseNumber)
                                .usingConverter(TestConverters::convertHouseNumberEntity)
                                .to(AddressDtoMappingDsl.$this.setHouseNumber)
                                .when(houseNumberEntity -> houseNumberEntity.getNumber() > 0)
                                .build())
        );
    }

}
