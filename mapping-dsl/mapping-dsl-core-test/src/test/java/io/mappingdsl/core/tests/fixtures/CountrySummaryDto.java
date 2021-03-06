package io.mappingdsl.core.tests.fixtures;

import io.mappingdsl.core.tests.fixtures.common.NamedObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CountrySummaryDto extends NamedObject {

    @Getter
    @Setter
    private String primaryCurrency;

    @Getter
    @Setter
    private int numberOfCurrencies;

    public CountrySummaryDto(String name) {
        super(name);
    }

}
