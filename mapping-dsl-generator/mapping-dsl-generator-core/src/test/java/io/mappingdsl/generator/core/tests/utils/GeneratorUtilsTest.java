package io.mappingdsl.generator.core.tests.utils;

import io.mappingdsl.generator.core.utils.GeneratorUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorUtilsTest {

    @Test
    void shouldGenerateProperDslWrapperClassName() {
        assertThat(GeneratorUtils.getDslWrapperClassName("CountryTransport"))
                .isEqualTo("CountryTransportMappingDsl");
    }

}
