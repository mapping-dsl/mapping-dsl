package io.mappingdsl.mavenplugin.test.thirdparty;

import org.junit.jupiter.api.Test;
import org.thirdpatry.lib.SimpleDto;
import org.thirdpatry.lib.SimpleEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ThirdPartyMapperTest {

    @Test
    void shouldPerformMapping() {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setVal("whatever");

        SimpleDto simpleDto = new ThirdPartyMapper().map(simpleEntity);

        assertThat(simpleDto.getVal()).isEqualTo("whatever");
    }

}
