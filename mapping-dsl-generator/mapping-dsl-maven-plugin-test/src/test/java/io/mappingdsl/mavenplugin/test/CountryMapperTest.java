package io.mappingdsl.mavenplugin.test;

import org.junit.jupiter.api.Test;
import org.thirdpatry.lib.CountryDto;
import org.thirdpatry.lib.CountryEntity;

import static org.assertj.core.api.Assertions.assertThat;

class CountryMapperTest {

    @Test
    void shouldPerformMapping() {
        CountryEntity country = new CountryEntity();
        country.setName("Ukraine");

        CountryDto countryDto = new CountryMapper().map(country);

        assertThat(countryDto.getName()).isEqualTo("Ukraine");
    }

}
