package io.mappingdsl.core.tests.utils;

import io.mappingdsl.core.common.BiConverter;
import io.mappingdsl.core.tests.fixtures.AddressDto;
import io.mappingdsl.core.tests.fixtures.AddressEntity;
import io.mappingdsl.core.tests.fixtures.CityDto;
import io.mappingdsl.core.tests.fixtures.CityEntity;
import io.mappingdsl.core.tests.fixtures.DistrictDto;
import io.mappingdsl.core.tests.fixtures.DistrictEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.SettlementDto;
import io.mappingdsl.core.tests.fixtures.SettlementEntity;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConverters {

    public static final BiConverter<Integer, String> intToStringConverter = new BiConverter<Integer, String>() {

        @Override
        public String convertForward(Integer source) {
            return String.valueOf(source);
        }

        @Override
        public Integer convertBackward(String target) {
            return Integer.valueOf(target);
        }

    };

    public static final BiConverter<String, String> phoneNumberConverter = new BiConverter<String, String>() {

        @Override
        public String convertForward(String source) {
            return "+" + source;
        }

        @Override
        public String convertBackward(String target) {
            return target + ";";
        }

    };

    public static HouseNumberDto convertHouseNumberEntity(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

    public static HouseNumberEntity convertHouseNumberDto(HouseNumberDto houseNumberDto) {
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity();
        houseNumberEntity.setNumber(houseNumberDto.getNumber());
        houseNumberEntity.setSuffix(houseNumberDto.getSuffix());
        houseNumberEntity.setGeolocation(houseNumberDto.getGeolocation());
        return houseNumberEntity;
    }

    public static final BiConverter<HouseNumberEntity, HouseNumberDto> houseNumberConverter =
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

    public static StreetDto convertStreetEntity(StreetEntity streetEntity) {
        return new StreetDto(streetEntity.getName());
    }

    public static StreetEntity convertStreetDto(StreetDto streetDto) {
        return new StreetEntity(streetDto.getName());
    }

    public static AddressDto convertAddressEntity(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto(
                convertStreetEntity(addressEntity.getStreet()),
                convertHouseNumberEntity(addressEntity.getHouseNumber()));

        addressDto.setPhoneNumbers(addressEntity.getPhoneNumbers());

        return addressDto;
    }

    public static AddressEntity convertAddressDto(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity(
                convertStreetDto(addressDto.getStreet()),
                convertHouseNumberDto(addressDto.getHouseNumber()));

        addressEntity.setPhoneNumbers(addressDto.getPhoneNumbers());

        return addressEntity;
    }

    public static final BiConverter<AddressEntity, AddressDto> addressConverter =
            new BiConverter<AddressEntity, AddressDto>() {

                @Override
                public AddressDto convertForward(AddressEntity source) {
                    return convertAddressEntity(source);
                }

                @Override
                public AddressEntity convertBackward(AddressDto target) {
                    return convertAddressDto(target);
                }

            };

    public static DistrictDto convertDistrictEntity(DistrictEntity districtEntity) {
        return new DistrictDto(districtEntity.getName());
    }

    public static SettlementDto convertCityEntity(SettlementEntity settlementEntity) {
        CityEntity cityEntity = (CityEntity) settlementEntity;
        DistrictDto[] districts = cityEntity.getDistricts() == null
                ? new DistrictDto[] {}
                : Arrays.stream(cityEntity.getDistricts())
                        .map(TestConverters::convertDistrictEntity)
                        .toArray(DistrictDto[]::new);

        return new CityDto(settlementEntity.getName(), districts);
    }

}
