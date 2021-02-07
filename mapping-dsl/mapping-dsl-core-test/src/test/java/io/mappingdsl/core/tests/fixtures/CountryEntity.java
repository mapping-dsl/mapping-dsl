package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class CountryEntity extends NamedObject {

    @Getter
    @Setter
    private SettlementEntity capital;

    @Getter
    @Setter
    private List<? extends SettlementEntity> biggestCities;

    @Getter
    @Setter
    private Number population;

    public CountryEntity(String name) {
        super(name);
    }

}
