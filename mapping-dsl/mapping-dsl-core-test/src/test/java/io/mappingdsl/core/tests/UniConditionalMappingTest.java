package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UniConditionalMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    void shouldMapConditionally(String testName, MappingDsl mappingDsl) {
        // passed condition
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity(221, "B");
        HouseNumberDto resultHouseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);
        assertThat(resultHouseNumberDto.getNumber()).isEqualTo(221);
        assertThat(resultHouseNumberDto.getSuffix()).isEqualTo("B");

        // rejected condition
        houseNumberEntity = new HouseNumberEntity(-221, "B");
        resultHouseNumberDto = mappingDsl.map(houseNumberEntity, HouseNumberDto.class);
        assertThat(resultHouseNumberDto.getNumber()).isNull();
        assertThat(resultHouseNumberDto.getSuffix()).isEqualTo("B");
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        "[uni] condition over fields",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.number)
                                .to(HouseNumberDtoMappingDsl.$this.number)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[uni] condition over properties",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.numberProperty)
                                .to(HouseNumberDtoMappingDsl.$this.numberProperty)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build()),

                Arguments.of(
                        "[uni] condition over methods",

                        new MappingDslBuilder()
                                .uniMapping()
                                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                                .produce(HouseNumberEntityMappingDsl.$this.getNumber)
                                .to(HouseNumberDtoMappingDsl.$this.setNumber)
                                .when(source -> source > 100)
                                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                                .to(HouseNumberDtoMappingDsl.$this.suffix)
                                .build())
        );
    }

    @Test
    void shouldMapCollectionElementsConditionally() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("+123", "+456", "789"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                .when(phoneNumber -> phoneNumber.startsWith("+"))
                .build();

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getPhoneNumbers()).containsExactly("+123", "+456");
    }

}
