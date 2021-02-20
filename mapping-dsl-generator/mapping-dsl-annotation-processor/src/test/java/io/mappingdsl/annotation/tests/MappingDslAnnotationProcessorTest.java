package io.mappingdsl.annotation.tests;

import com.google.testing.compile.Compilation;
import io.mappingdsl.annotation.MappingDslAnnotationProcessor;
import org.junit.jupiter.api.Test;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;
import static com.google.testing.compile.JavaFileObjects.forResource;

class MappingDslAnnotationProcessorTest {

    @Test
    void shouldGenerateSimpleDsl() {
        Compilation compilation = javac()
                .withProcessors(new MappingDslAnnotationProcessor())
                .compile(
                        forResource("fixtures/input/AnotherType.java"),
                        forResource("fixtures/input/SimpleType.java"));

        assertThat(compilation).succeeded();

        assertThat(compilation)
                .generatedSourceFile("pojo.AnotherTypeMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/AnotherTypeMappingDsl.java"));

        assertThat(compilation)
                .generatedSourceFile("pojo.SimpleTypeMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/SimpleTypeMappingDsl.java"));

    }

}
