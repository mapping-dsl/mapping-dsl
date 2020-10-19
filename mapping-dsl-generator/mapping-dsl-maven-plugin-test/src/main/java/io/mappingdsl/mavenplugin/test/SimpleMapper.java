package io.mappingdsl.mavenplugin.test;

import io.mappingdsl.core.MapperDsl;
import io.mappingdsl.core.builder.DslMapperBuilder;
import org.thirdpatry.lib.SimpleDto;
import org.thirdpatry.lib.SimpleDtoMappingDsl;
import org.thirdpatry.lib.SimpleEntity;
import org.thirdpatry.lib.SimpleEntityMappingDsl;

public class SimpleMapper {

    public SimpleDto map(SimpleEntity entity) {
        MapperDsl mapper = new DslMapperBuilder()
                .uniMapping()
                .from(SimpleEntity.class).to(SimpleDto.class)
                .supply(SimpleEntityMappingDsl.$this.val).in(SimpleDtoMappingDsl.$this.val)
                .build();

        return mapper.map(entity, SimpleDto.class);
    }

}
