package io.mappingdsl.mavenplugin;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.util.ArrayList;
import java.util.List;

@Mojo(
        name = "generate-test-dsl",
        defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES,
        requiresDependencyResolution = ResolutionScope.COMPILE
)
public class TestDslGeneratorMojo extends AbstractDslGeneratorMojo {

    @Parameter
    public List<String> testSourceFiles = new ArrayList<>();

    @Parameter
    public List<String> testClassFiles = new ArrayList<>();

    @Parameter(defaultValue = "${project.build.testSourceDirectory}", readonly = true, required = true)
    private String testSourceDirectory;

    @Override
    public void execute() throws MojoFailureException {
        executeGeneration(this.testSourceDirectory, this.testSourceFiles, this.testClassFiles, "generated-test-sources");
    }

    @Override
    protected boolean isTestCompilation() {
        return true;
    }

}
