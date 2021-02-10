package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CityDto extends SettlementDto {

    @Getter
    @Setter
    private DistrictDto[] districts;

    public CityDto(String name) {
        super(name);
    }

    public CityDto(DistrictDto[] districts) {
        this.districts = districts;
    }

    public CityDto(String name, DistrictDto[] districts) {
        super(name);
        this.districts = districts;
    }

}
