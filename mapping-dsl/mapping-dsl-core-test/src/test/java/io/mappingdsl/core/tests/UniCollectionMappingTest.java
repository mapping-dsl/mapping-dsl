package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class UniCollectionMappingTest {

    @Test
    void shouldMapCollection() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPhoneNumbers(Arrays.asList("123", "456", "789"));

        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.phoneNumbers)
                .to(AddressDtoMappingDsl.$this.phoneNumbers)
                .build();

        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(addressDto.getPhoneNumbers()).containsExactly("123", "456", "789");
    }

}
