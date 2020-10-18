package io.mappingdsl.mavenplugin.test;

import io.mappingdsl.core.MapperDsl;
import io.mappingdsl.core.builder.DslMapperBuilder;
import org.thirdpatry.lib.CountryDto;
import org.thirdpatry.lib.CountryDtoMappingDsl;
import org.thirdpatry.lib.CountryEntity;
import org.thirdpatry.lib.CountryEntityMappingDsl;

public class CountryMapper {

    public CountryDto map(CountryEntity country) {
        MapperDsl mapper = new DslMapperBuilder()
                .uniMapping()
                .from(CountryEntity.class).to(CountryDto.class)
                .supply(CountryEntityMappingDsl.$this.name).in(CountryDtoMappingDsl.$this.name)
                .build();

        return mapper.map(country, CountryDto.class);
    }

}
