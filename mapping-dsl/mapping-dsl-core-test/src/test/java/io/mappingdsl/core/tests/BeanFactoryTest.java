package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.MappingBeanFactory;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.AddressEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BeanFactoryTest {

    @Test
    void shouldCreateTargetObjectViaBeanFactoryForUniMapping() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory())
                .uniMapping()
                .from(AddressEntity.class).to(AddressDto.class)
                .produce(AddressEntityMappingDsl.$this.street.name).to(AddressDtoMappingDsl.$this.street.name)
                .build();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        AddressEntity addressEntity = new AddressEntity(streetEntity, null);
        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);
    }

    @Test
    void shouldCreateTargetObjectViaBeanFactoryForBiMapping() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory())
                .onCreate(AddressEntity.class).useFactory(new AddressEntityBeanFactory())
                .biMapping()
                .between(AddressEntity.class).and(AddressDto.class)
                .bind(AddressEntityMappingDsl.$this.street.name).with(AddressDtoMappingDsl.$this.street.name)
                .build();

        StreetEntity streetEntity = new StreetEntity("Baker Street");
        AddressEntity addressEntity = new AddressEntity(streetEntity, null);
        AddressDto addressDto = mappingDsl.map(addressEntity, AddressDto.class);

        assertThat(addressDto.getStreet().getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(addressDto.getHouseNumber().getNumber()).isEqualTo(221);

        StreetDto streetDto = new StreetDto("Baker Street");
        addressDto = new AddressDto(streetDto, null);
        addressEntity = mappingDsl.map(addressDto, AddressEntity.class);

        assertThat(addressEntity.getStreet().getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(addressEntity.getHouseNumber().getNumber()).isEqualTo(221);
    }

    @Test
    void shouldThrowErrorOnFactoryDefinitionDuplicate() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> new MappingDslBuilder()
                        .configuration()
                        .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory())
                        .onCreate(AddressDto.class).useFactory(new AddressDtoBeanFactory()))
                .withMessage("Bean Factory has been already set for type: io.mappingdsl.core.tests.fixtures.AddressDto");
    }

    private static final class AddressDtoBeanFactory implements MappingBeanFactory<AddressDto> {

        @Override
        public AddressDto create(Object source, Class<AddressDto> targetType) {
            return new AddressDto(null, new HouseNumberDto(221));
        }

    }

    private static final class AddressEntityBeanFactory implements MappingBeanFactory<AddressEntity> {

        @Override
        public AddressEntity create(Object source, Class<AddressEntity> targetType) {
            return new AddressEntity(null, new HouseNumberEntity(221));
        }

    }

}
