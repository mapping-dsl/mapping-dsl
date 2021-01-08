package io.mappingdsl.core.tests.utils;

import io.mappingdsl.core.execution.MappingBeanFactory;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestFactories {

    public static final class AddressDtoBeanFactory implements MappingBeanFactory<AddressDto> {

        private final StreetDto street;
        private final HouseNumberDto houseNumber;

        public AddressDtoBeanFactory(StreetDto street, HouseNumberDto houseNumber) {
            this.street = street;
            this.houseNumber = houseNumber;
        }

        @Override
        public AddressDto create(Object source, Class<AddressDto> targetType) {
            return new AddressDto(this.street, this.houseNumber);
        }

    }

    public static final class AddressEntityBeanFactory implements MappingBeanFactory<AddressEntity> {

        private final StreetEntity street;
        private final HouseNumberEntity houseNumber;

        public AddressEntityBeanFactory(StreetEntity street, HouseNumberEntity houseNumber) {
            this.street = street;
            this.houseNumber = houseNumber;
        }

        @Override
        public AddressEntity create(Object source, Class<AddressEntity> targetType) {
            return new AddressEntity(this.street, this.houseNumber);
        }

    }

}
