package io.mappingdsl.core.tests.fixtures;

import io.mappingdsl.core.tests.fixtures.common.NamedObject;
import io.mappingdsl.core.tests.fixtures.common.timezone.TimeZone;
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

    @Getter
    @Setter
    private CharSequence[] nationalLanguages;

    @Getter
    @Setter
    private Iterable<String> nationalCurrencies;

    @Getter
    @Setter
    private List<TimeZone> timeZones;

    public CountryDto(String name) {
        super(name);
    }

}
