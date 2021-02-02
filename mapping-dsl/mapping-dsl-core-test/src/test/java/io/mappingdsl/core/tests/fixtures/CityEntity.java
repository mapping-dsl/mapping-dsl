package io.mappingdsl.core.tests.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CityEntity extends SettlementEntity {

    @Getter
    @Setter
    private DistrictEntity[] districts;

    public CityEntity(String name) {
        super(name);
    }

}