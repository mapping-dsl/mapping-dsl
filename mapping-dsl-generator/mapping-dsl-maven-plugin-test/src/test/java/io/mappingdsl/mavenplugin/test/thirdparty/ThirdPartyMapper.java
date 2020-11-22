package io.mappingdsl.mavenplugin.test.thirdparty;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import org.thirdpatry.lib.SimpleDto;
import org.thirdpatry.lib.SimpleDtoMappingDsl;
import org.thirdpatry.lib.SimpleEntity;
import org.thirdpatry.lib.SimpleEntityMappingDsl;

public class ThirdPartyMapper {

    public SimpleDto map(SimpleEntity entity) {
        MappingDsl mapper = new MappingDslBuilder()
                .uniMapping()
                .from(SimpleEntity.class).to(SimpleDto.class)
                .produce(SimpleEntityMappingDsl.$this.val).to(SimpleDtoMappingDsl.$this.val)
                .build();

        return mapper.map(entity, SimpleDto.class);
    }

}
