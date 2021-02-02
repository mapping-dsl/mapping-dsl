package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CountryDto extends NamedObject {

    @Getter
    @Setter
    private SettlementDto capital;

    @Getter
    @Setter
    private Number population;

    public CountryDto(String name) {
        super(name);
    }

}