package io.mappingdsl.core.tests.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CityDto extends SettlementDto {

    @Getter
    @Setter
    private DistrictDto[] districts;

    public CityDto(String name) {
        super(name);
    }

}
