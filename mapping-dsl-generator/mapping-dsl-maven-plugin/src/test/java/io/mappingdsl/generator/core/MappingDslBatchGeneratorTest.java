package io.mappingdsl.generator.core;

import com.google.testing.compile.Compilation;
import io.mappingdsl.mavenplugin.MappingDslBatchGenerator;
import org.junit.jupiter.api.Test;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;
import static com.google.testing.compile.JavaFileObjects.forResource;

class MappingDslBatchGeneratorTest {

    @Test
    void shouldGenerateSimpleDsl() {
        Compilation compilation = javac()
                .withProcessors(new MappingDslBatchGenerator())
                .withOptions("-Ascope=pojo.SimpleType")
                .compile(forResource("fixtures/input/SimpleType.java"));

        assertThat(compilation).succeeded();

        assertThat(compilation)
                .generatedSourceFile("pojo.SimpleTypeMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/SimpleTypeMappingDsl.java"));
    }

}
