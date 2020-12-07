package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.MappingBeanFactory;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BeanFactoryTest {

    @Test
    void shouldCreateTargetObjectViaBeanFactoryForUniMapping() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(StreetDto.class).useFactory(new StreetDtoBeanFactory())
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        StreetDto streetDto = mappingDsl.map(new StreetEntity("Baker Street"), StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
    }

    @Test
    void shouldCreateTargetObjectViaBeanFactoryForBiMapping() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onCreate(StreetDto.class).useFactory(new StreetDtoBeanFactory())
                .onCreate(StreetEntity.class).useFactory(new StreetEntityBeanFactory())
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name).with(StreetDtoMappingDsl.$this.name)
                .build();

        StreetDto streetDto = mappingDsl.map(new StreetEntity("Baker Street"), StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);

        StreetEntity streetEntity = mappingDsl.map(new StreetDto("Baker Street"), StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");

        // added by bean factory
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
    }

    @Test
    void shouldThrowErrorOnFactoryDefinitionDuplicate() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> new MappingDslBuilder()
                        .configuration()
                        .onCreate(StreetDto.class).useFactory(new StreetDtoBeanFactory())
                        .onCreate(StreetDto.class).useFactory(new StreetDtoBeanFactory()))
                .withMessage("Bean Factory has been already set for type: io.mappingdsl.core.tests.fixtures.StreetDto");
    }

    private static final class StreetDtoBeanFactory implements MappingBeanFactory<StreetDto> {

        @Override
        public StreetDto create(Object source, Class<StreetDto> targetType) {
            return new StreetDto(null, new HouseNumberDto(221));
        }

    }

    private static final class StreetEntityBeanFactory implements MappingBeanFactory<StreetEntity> {

        @Override
        public StreetEntity create(Object source, Class<StreetEntity> targetType) {
            return new StreetEntity(null, new HouseNumberEntity(221));
        }

    }

}
