package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.execution.NoMappingException;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NoDefinedMappingTest {

    @Test
    void shouldFailIfNoMappingDefinedAndTerminateModeSet() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onMissingMapping().terminate()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        assertThatThrownBy(() -> mappingDsl.map(BigInteger.valueOf(123), Integer.class))
                .isInstanceOf(NoMappingException.class)
                .hasMessage("No mapping defined for " +
                        "MappingKey(source=class java.math.BigInteger, target=class java.lang.Integer)");
    }

    @Test
    void shouldReturnNullByDefaultIfNoMappingDefined() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        Integer mappedValue = mappingDsl.map(BigInteger.valueOf(123), Integer.class);
        assertThat(mappedValue).isNull();
    }

    @Test
    void shouldReturnNullIfNoMappingDefinedAndReturnNullModeSet() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .configuration()
                .onMissingMapping().returnNull()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name).to(StreetDtoMappingDsl.$this.name)
                .build();

        Integer mappedValue = mappingDsl.map(BigInteger.valueOf(123), Integer.class);
        assertThat(mappedValue).isNull();
    }

}
