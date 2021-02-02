package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CountryEntity extends NamedObject {

    @Getter
    @Setter
    private SettlementEntity capital;

    @Getter
    @Setter
    private Number population;

    public CountryEntity(String name) {
        super(name);
    }

}