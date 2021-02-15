package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CountrySummaryEntity extends NamedObject {

    @Getter
    @Setter
    private String primaryCurrency;

    @Getter
    @Setter
    private int numberOfCurrencies;

    public CountrySummaryEntity(String name) {
        super(name);
    }

}
