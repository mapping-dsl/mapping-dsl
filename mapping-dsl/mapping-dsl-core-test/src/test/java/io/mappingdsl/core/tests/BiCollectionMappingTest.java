package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NoMappingException;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.utils.BiMappingTestFlow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BiCollectionMappingTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("simpleTestData")
    void shouldMapCollectionOfSimpleValues(String testName, MappingDsl mappingDsl, BiMappingTestFlow testFlow) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        // forward mapping
        AddressDto addressDto;

        try {
            addressDto = mappingDsl.map(addressEntity, AddressDto.class);
        }
        catch (NoMappingException ignore) {
            addressDto = null;
        }

        if (testFlow.isForwardMapped()) {
            assertThat(addressDto.getPhoneNumbers()).containsExactly("123", "456", "789");
        }
        else {
            assertThat(addressDto).isNull();
        }

        // refresh test entity for backward mapping
        addressDto = new AddressDto();
        addressDto.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        // backward mapping
        try {
            addressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        }
        catch (NoMappingException ignore) {
            addressEntity = null;
        }

        if (testFlow.isBackwardMapped()) {
            assertThat(addressEntity.getPhoneNumbers()).containsExactly("123", "456", "789");
        }
        else {
            assertThat(addressEntity).isNull();
        }
    }

    private static Stream<Arguments> simpleTestData() {
        return Stream.of(
                Arguments.of(
                        "[bi] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .bind(AddressEntityMappingDsl.$this.phoneNumbers)
                                .with(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(true)
                                .build()),
                Arguments.of(
                        "[forward] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(true)
                                .backwardMapped(false)
                                .build()),

                Arguments.of(
                        "[backward] collection of simple values mapping",

                        new MappingDslBuilder()
                                .biMapping()
                                .between(AddressEntity.class).and(AddressDto.class)
                                .consume(AddressEntityMappingDsl.$this.phoneNumbers)
                                .from(AddressDtoMappingDsl.$this.phoneNumbers)
                                .build(),

                        BiMappingTestFlow.builder()
                                .forwardMapped(false)
                                .backwardMapped(true)
                                .build()));
    }

}
