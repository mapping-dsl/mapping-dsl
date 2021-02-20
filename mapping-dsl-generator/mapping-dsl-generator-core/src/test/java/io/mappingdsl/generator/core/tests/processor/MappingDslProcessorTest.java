package io.mappingdsl.generator.core.tests.processor;

import com.google.testing.compile.Compilation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;
import static com.google.testing.compile.JavaFileObjects.forResource;

class MappingDslProcessorTest {

    @Test
    void shouldGenerateSimpleDsl() {
        Compilation compilation = javac()
                .withProcessors(new TestAnnotationProcessor(Collections.singletonList("pojo.SimpleField")))
                .compile(forResource("fixtures/input/SimpleField.java"));

        assertThat(compilation).succeeded();

        assertThat(compilation)
                .generatedSourceFile("pojo.SimpleFieldMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/SimpleFieldMappingDsl.java"));
    }

    @Test
    void shouldGenerateNestedDsl() {
        Compilation compilation = javac()
                .withProcessors(new TestAnnotationProcessor(Arrays.asList(
                        "pojo.SimpleField", "pojo.ComplexField", "pojo.AbstractValue")))
                .compile(
                        forResource("fixtures/input/SimpleField.java"),
                        forResource("fixtures/input/ComplexField.java"),
                        forResource("fixtures/input/AbstractValue.java"),
                        forResource("fixtures/input/InterfaceValue.java"));

        assertThat(compilation).succeeded();

        assertThat(compilation)
                .generatedSourceFile("pojo.AbstractValueMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/AbstractValueMappingDsl.java"));

        assertThat(compilation)
                .generatedSourceFile("pojo.SimpleFieldMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/SimpleFieldMappingDsl.java"));

        assertThat(compilation)
                .generatedSourceFile("pojo.ComplexFieldMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/ComplexFieldMappingDsl.java"));
    }

}
