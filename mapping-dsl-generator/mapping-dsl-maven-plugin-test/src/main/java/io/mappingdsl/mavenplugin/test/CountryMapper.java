package io.mappingdsl.mavenplugin.test;

import io.mappingdsl.core.MapperDsl;
import io.mappingdsl.core.builder.DslMapperBuilder;
import org.thirdpatry.lib.Country;
import org.thirdpatry.lib.CountryMappingDsl;
import org.thirdpatry.lib.CountryTransport;
import org.thirdpatry.lib.CountryTransportMappingDsl;

public class CountryMapper {

    public CountryTransport map(Country country) {
        MapperDsl mapper = new DslMapperBuilder()
                .uniMapping()
                .from(Country.class).to(CountryTransport.class)
                .supply(CountryMappingDsl.$this.name).in(CountryTransportMappingDsl.$this.name)
                .build();

        return mapper.map(country, CountryTransport.class);
    }

}
