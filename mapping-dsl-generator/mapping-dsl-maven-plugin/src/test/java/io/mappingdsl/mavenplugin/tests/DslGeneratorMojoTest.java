package io.mappingdsl.mavenplugin.tests;

import ice.bricks.meta.ClassUtils;
import ice.bricks.reflection.ReflectionUtils;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import io.mappingdsl.mavenplugin.DslGeneratorMojo;
import io.mappingdsl.mavenplugin.tests.fixtures.SimplePojo;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DslGeneratorMojoTest {

    @Test
    void shouldGenerateDslWrapper() throws Exception {
        String testClassName = SimplePojo.class.getCanonicalName();

        DslGeneratorMojo mojo = preparePluginMojo();
        mojo.sourceFiles.add(testClassName);
        mojo.execute();

        File generatedDsl = getGeneratedFile(testClassName);
        File expectedDsl = getExpectedFile(testClassName);
        assertThat(generatedDsl).hasSameTextualContentAs(expectedDsl);
    }

    private DslGeneratorMojo preparePluginMojo() {
        DslGeneratorMojo mojo = new DslGeneratorMojo();

        ReflectionUtils.writeField(mojo, "project", new MavenProject());
        ReflectionUtils.writeField(mojo, "sourceDirectory", "src/test/java");

        Artifact mappingDslCore = mock(Artifact.class);
        when(mappingDslCore.getFile()).thenReturn(new File("./../../mapping-dsl/mapping-dsl-core/target/classes"));
        ReflectionUtils.writeField(mojo, "pluginArtifacts", Collections.singletonList(mappingDslCore));

        ReflectionUtils.writeField(mojo, "targetDirectory", "target");

        return mojo;
    }

    private File getGeneratedFile(String testClassName) {
        return new File(String.join(File.separator,
                "target/generated-sources/java",
                ClassUtils.getClassPackage(testClassName).replace(".", File.separator),
                GeneratorUtils.getDslWrapperClassName(testClassName) + ".java"
        ));
    }

    private File getExpectedFile(String testClassName) {
        return new File(String.join(File.separator,
                "src/test/resources",
                GeneratorUtils.getDslWrapperClassName(testClassName) + ".java"));
    }

}
