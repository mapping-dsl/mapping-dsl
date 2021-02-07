package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class CountryDto extends NamedObject {

    @Getter
    @Setter
    private SettlementDto capital;

    @Getter
    @Setter
    private List<? extends SettlementDto> biggestCities;

    @Getter
    @Setter
    private Number population;

    public CountryDto(String name) {
        super(name);
    }

}
