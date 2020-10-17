package io.mappingdsl.generator.core.tests.processor;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;
import io.mappingdsl.generator.core.GeneratorScopeProcessor;
import org.junit.jupiter.api.Test;

class GeneratorScopeProcessorTest {

    @Test
    void shouldGenerateSimpleDsl() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new GeneratorScopeProcessor())
                .withOptions("-Ascope=pojo.ZipCode")
                .compile(JavaFileObjects.forResource("fixtures/input/ZipCode.java"));

        CompilationSubject.assertThat(compilation).succeeded();

        CompilationSubject.assertThat(compilation)
                .generatedSourceFile("pojo.ZipCodeMappingDsl")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("fixtures/output/ZipCodeMappingDsl.java"));
    }

}