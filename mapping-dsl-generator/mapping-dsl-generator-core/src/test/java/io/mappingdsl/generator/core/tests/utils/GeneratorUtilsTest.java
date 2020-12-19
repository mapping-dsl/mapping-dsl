package io.mappingdsl.generator.core.tests.utils;

import io.mappingdsl.generator.core.utils.GeneratorUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorUtilsTest {

    @Test
    void shouldGenerateProperDslClassName() {
        assertThat(GeneratorUtils.getDslClassName("CountryTransport"))
                .isEqualTo("CountryTransportMappingDsl");

        assertThat(GeneratorUtils.getDslClassName("pojo.CountryTransport"))
                .isEqualTo("CountryTransportMappingDsl");
    }

}
