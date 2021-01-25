package io.mappingdsl.core.tests.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CityOverviewEntity {

    @Getter
    @Setter
    private int numberOfDistricts;

    @Getter
    @Setter
    private DistrictEntity centralDistrict;

}
