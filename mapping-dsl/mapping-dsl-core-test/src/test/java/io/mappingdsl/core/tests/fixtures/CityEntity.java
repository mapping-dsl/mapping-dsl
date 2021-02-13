package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CityEntity extends SettlementEntity {

    @Getter
    @Setter
    private DistrictEntity[] districts;

    public CityEntity(String name) {
        super(name);
    }

    public CityEntity(DistrictEntity[] districts) {
        this.districts = districts;
    }

    public CityEntity(String name, DistrictEntity[] districts) {
        super(name);
        this.districts = districts;
    }

}
