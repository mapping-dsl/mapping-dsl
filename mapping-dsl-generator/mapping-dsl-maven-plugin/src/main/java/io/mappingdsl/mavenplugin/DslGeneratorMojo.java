package io.mappingdsl.mavenplugin;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.util.ArrayList;
import java.util.List;

@Mojo(
        name = "generate-dsl",
        defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        requiresDependencyResolution = ResolutionScope.COMPILE
)
public class DslGeneratorMojo extends AbstractDslGeneratorMojo {

    @Parameter
    public List<String> sourceFiles = new ArrayList<>();

    @Parameter
    public List<String> classFiles = new ArrayList<>();

    @Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true, required = true)
    private String sourceDirectory;

    @Override
    public void execute() throws MojoFailureException {
        executeGeneration(this.sourceDirectory, this.sourceFiles, this.classFiles, "generated-sources");
    }

    @Override
    protected boolean isTestCompilation() {
        return false;
    }

}
