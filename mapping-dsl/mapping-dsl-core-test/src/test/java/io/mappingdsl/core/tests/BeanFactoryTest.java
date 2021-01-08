package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.utils.TestFactories.AddressDtoBeanFactory;
import io.mappingdsl.core.tests.utils.TestFactories.AddressEntityBeanFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BeanFactoryTest {

    @Test
    void shouldCreateTargetObjectViaBeanFactoryForUniMapping() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory(null, new HouseNumberDto(221)))
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.street.name).to(AddressDtoMappingDsl.$this.street.name)
                .build();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        AddressEntity addressEntity = new AddressEntity(streetEntity, null);

        AddressDto resultAddressDto = mappingDsl.map(addressEntity, AddressDto.class);
        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);
    }

    @Test
    void shouldCreateTargetObjectViaBeanFactoryForBiMapping() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory(null, new HouseNumberDto(221)))
                .onCreate(AddressEntity.class).useFactory(new AddressEntityBeanFactory(null, new HouseNumberEntity(221)))
                .biMapping()
                .between(AddressEntity.class).and(AddressDto.class)
                .bind(AddressEntityMappingDsl.$this.street.name).with(AddressDtoMappingDsl.$this.street.name)
                .build();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        AddressDto resultAddressDto = mappingDsl.map(new AddressEntity(streetEntity, null), AddressDto.class);
        assertThat(resultAddressDto.getStreet().getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(resultAddressDto.getHouseNumber().getNumber()).isEqualTo(221);

        StreetDto streetDto = new StreetDto("Baker Street");
        AddressDto addressDto = new AddressDto(streetDto, null);
        AddressEntity resultAddressEntity = mappingDsl.map(addressDto, AddressEntity.class);
        assertThat(resultAddressEntity.getStreet().getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(resultAddressEntity.getHouseNumber().getNumber()).isEqualTo(221);
    }

    @Test
    void shouldThrowErrorOnFactoryDefinitionDuplicate() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> new MappingDslBuilder()
                        .configuration()
                        .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory(null, null))
                        .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory(null, null)))
                .withMessage("Bean Factory has been already set for type: io.mappingdsl.core.tests.fixtures.AddressDto");
    }

}
