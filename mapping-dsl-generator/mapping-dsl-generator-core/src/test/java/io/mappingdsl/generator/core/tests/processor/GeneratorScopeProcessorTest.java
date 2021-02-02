package io.mappingdsl.generator.core.tests.processor;

import com.google.testing.compile.Compilation;
import io.mappingdsl.generator.core.GeneratorScopeProcessor;
import org.junit.jupiter.api.Test;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;
import static com.google.testing.compile.JavaFileObjects.forResource;

class GeneratorScopeProcessorTest {

    @Test
    void shouldGenerateSimpleDsl() {
        Compilation compilation = javac()
                .withProcessors(new GeneratorScopeProcessor())
                .withOptions("-Ascope=pojo.SimpleField")
                .compile(forResource("fixtures/input/SimpleField.java"));

        assertThat(compilation).succeeded();

        assertThat(compilation)
                .generatedSourceFile("pojo.SimpleFieldMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/SimpleFieldMappingDsl.java"));
    }

    @Test
    void shouldGenerateNestedDsl() {
        Compilation compilation = javac()
                .withProcessors(new GeneratorScopeProcessor())
                .withOptions("-Ascope=pojo.SimpleField,pojo.ComplexField")
                .compile(
                        forResource("fixtures/input/SimpleField.java"),
                        forResource("fixtures/input/ComplexField.java"),
                        forResource("fixtures/input/AbstractValue.java"),
                        forResource("fixtures/input/InterfaceValue.java"));

        assertThat(compilation).succeeded();

        assertThat(compilation)
                .generatedSourceFile("pojo.SimpleFieldMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/SimpleFieldMappingDsl.java"));

        assertThat(compilation)
                .generatedSourceFile("pojo.ComplexFieldMappingDsl")
                .hasSourceEquivalentTo(forResource("fixtures/output/ComplexFieldMappingDsl.java"));
    }

}
