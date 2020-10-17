package io.mappingdsl.mavenplugin.test;

import org.junit.jupiter.api.Test;
import org.thirdpatry.lib.Country;
import org.thirdpatry.lib.CountryTransport;

import static org.assertj.core.api.Assertions.assertThat;

class CountryMapperTest {

    @Test
    void shouldPerformMapping() {
        Country country = new Country();
        country.setName("Ukraine");

        CountryTransport countryTransport = new CountryMapper().map(country);

        assertThat(countryTransport.getName()).isEqualTo("Ukraine");
    }

}
