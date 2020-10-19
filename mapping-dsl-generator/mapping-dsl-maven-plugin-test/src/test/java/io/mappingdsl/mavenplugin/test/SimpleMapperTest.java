package io.mappingdsl.mavenplugin.test;

import org.junit.jupiter.api.Test;
import org.thirdpatry.lib.SimpleDto;
import org.thirdpatry.lib.SimpleEntity;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleMapperTest {

    @Test
    void shouldPerformMapping() {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setVal("whatever");

        SimpleDto simpleDto = new SimpleMapper().map(simpleEntity);

        assertThat(simpleDto.getVal()).isEqualTo("whatever");
    }

}
