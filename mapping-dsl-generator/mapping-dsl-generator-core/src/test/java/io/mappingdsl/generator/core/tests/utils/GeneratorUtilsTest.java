package io.mappingdsl.generator.core.tests.utils;

import io.mappingdsl.generator.core.utils.GeneratorUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorUtilsTest {

    @Test
    void shouldGenerateProperDslWrapperClassName() {
        assertThat(GeneratorUtils.getDslWrapperClassName("CountryTransport"))
                .isEqualTo("CountryTransportMappingDsl");

        assertThat(GeneratorUtils.getDslWrapperClassName("pojo.CountryTransport"))
                .isEqualTo("CountryTransportMappingDsl");
    }

    @Test
    void shouldExtractClassPackage() {
        assertThat(GeneratorUtils.getClassPackage("pojo.CountryTransport"))
                .isEqualTo("pojo");

        assertThat(GeneratorUtils.getClassPackage("CountryTransport"))
                .isNull();
    }

    @Test
    void shouldExtractClassName() {
        assertThat(GeneratorUtils.getClassName("pojo.CountryTransport"))
                .isEqualTo("CountryTransport");

        assertThat(GeneratorUtils.getClassName("CountryTransport"))
                .isEqualTo("CountryTransport");
    }

}
