package io.mappingdsl.core.test;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NoMappingException;
import io.mappingdsl.core.test.fixtures.StreetDto;
import io.mappingdsl.core.test.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.StreetEntity;
import io.mappingdsl.core.test.fixtures.StreetEntityMappingDsl;
import io.mappingdsl.core.test.fixtures.ZipDto;
import io.mappingdsl.core.test.fixtures.ZipDtoMappingDsl;
import io.mappingdsl.core.test.fixtures.ZipEntity;
import io.mappingdsl.core.test.fixtures.ZipEntityMappingDsl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class NoDefinedMappingTest {

    @Test
    void shouldFailIfNoMappingDefined() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .supply(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        Assertions.assertThatThrownBy(() -> mappingDsl.map(BigInteger.valueOf(123), Integer.class))
                .isInstanceOf(NoMappingException.class)
                .hasMessage("No mapping defined for " +
                        "MappingKey(source=class java.math.BigInteger, target=class java.lang.Integer)");
    }

}
