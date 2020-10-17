package io.mappingdsl.mavenplugin;

import ice.bricks.exceptions.ExceptionUtils;
import ice.bricks.io.IoUtils;
import io.mappingdsl.generator.core.GeneratorScopeProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractDslGeneratorMojo extends AbstractMojo {

    @Parameter
    public List<String> extraCompilerOptions = new ArrayList<>();

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter(defaultValue = "${plugin.artifacts}", readonly = true, required = true)
    private List<Artifact> pluginArtifacts = new ArrayList<>();

    @Parameter(defaultValue = "${project.build.sourceEncoding}", readonly = true, required = true)
    private String sourceEncoding;

    @Parameter(defaultValue = "${project.build.directory}", readonly = true, required = true)
    private String targetDirectory;

    protected void executeGeneration(
            String sourcesDirectory, List<String> sourceFiles, List<String> classFiles,
            String targetSubdirectory) throws MojoFailureException {

        if (CollectionUtils.isEmpty(sourceFiles) && CollectionUtils.isEmpty(classFiles)) {
            getLog().warn("Neither source files nor class files were supplied for DSL generation. Skipping execution.");
            return;
        }

        File outputDirectory = new File(this.targetDirectory, targetSubdirectory + File.separator + "java");
        outputDirectory.mkdirs();
        String outputDirectoryPath = outputDirectory.getAbsolutePath();

        getLog().debug("Target directory: " + outputDirectoryPath);

        if (isTestCompilation()) {
            this.project.addTestCompileSourceRoot(outputDirectoryPath);
        }
        else {
            this.project.addCompileSourceRoot(outputDirectoryPath);
        }

        List<String> compilerOptions = buildCompilerOptions(outputDirectory, classFiles, sourceFiles);

        getLog().debug("Compiler options: " + String.join(", ", compilerOptions));

        Writer compilationOutput = new StringWriter();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        Iterable<String> classesForCompilation = getClassesForCompilation(classFiles);
        Iterable<? extends JavaFileObject> sourcesForCompilation = getSourcesForCompilation(
                fileManager, sourceFiles, sourcesDirectory);

        CompilationTask compilationTask = compiler.getTask(
                compilationOutput, fileManager, diagnostics, compilerOptions,
                classesForCompilation, sourcesForCompilation);

        getLog().info("Generation started");

        Future<Boolean> compilationFutureResult = Executors.newSingleThreadExecutor().submit(compilationTask);
        boolean compilationSuccess = ExceptionUtils.runSafe(() -> compilationFutureResult.get());

        if (compilationSuccess) {
            getLog().info("Generation successfully finished");
            printDiagnostics(diagnostics);
        }
        else {
            getLog().error("Generation failed");
            printDiagnostics(diagnostics);

            String failReason = compilationOutput.toString();
            getLog().error(failReason);
            throw new MojoFailureException(failReason);
        }

        IoUtils.runSafe(fileManager::close);
    }

    protected abstract boolean isTestCompilation();

    private void printDiagnostics(DiagnosticCollector<JavaFileObject> diagnostics) {
        if (!diagnostics.getDiagnostics().isEmpty()) {
            getLog().debug("Diagnostics:");
            diagnostics.getDiagnostics().forEach(diagnostic ->
                    getLog().info(diagnostic.getMessage(Locale.getDefault())));
        }
    }

    private Iterable<String> getClassesForCompilation(List<String> classFiles) {
        return CollectionUtils.isEmpty(classFiles) ? null : classFiles;
    }

    private Iterable<? extends JavaFileObject> getSourcesForCompilation(
            StandardJavaFileManager fileManager, List<String> sourceFiles, String sourceDirectoryPath) {

        if (CollectionUtils.isEmpty(sourceFiles)) {
            return null;
        }

        File sourceDirectory = new File(sourceDirectoryPath);

        Set<File> sources = sourceFiles.stream()
                .map(sourceFile -> sourceFile.replace('.', File.separatorChar))
                .map(path -> new File(sourceDirectory, path + ".java"))
                .collect(Collectors.toSet());

        return fileManager.getJavaFileObjectsFromFiles(sources);
    }

    private List<String> buildCompilerOptions(File outputDirectory, List<String> classFiles, List<String> sourceFiles) {
        Map<String, String> compilerOptionsPairs = new LinkedHashMap<>();

        compilerOptionsPairs.put("cp", buildClassPath());

        if (this.sourceEncoding != null) {
            compilerOptionsPairs.put("encoding", this.sourceEncoding);
        }

        compilerOptionsPairs.put("proc:only", null);
        compilerOptionsPairs.put("processor", GeneratorScopeProcessor.class.getCanonicalName());

        compilerOptionsPairs.put("s", outputDirectory.getPath());

        String sourcePath = getSourceDirectories().stream()
                .map(directory -> IoUtils.runSafe(directory::getCanonicalPath))
                .collect(Collectors.joining(File.pathSeparator));

        compilerOptionsPairs.put("sourcepath", sourcePath);

        String scope = Stream.concat(sourceFiles.stream(), classFiles.stream()).collect(Collectors.joining(","));
        compilerOptionsPairs.put("Ascope=" + scope, null);

        List<String> compilerOptions = compilerOptionsPairs.entrySet().stream()
                .map(compilerOption -> Arrays.asList("-" + compilerOption.getKey(), compilerOption.getValue()))
                .flatMap(Collection::stream)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());

        compilerOptions.addAll(this.extraCompilerOptions);

        return compilerOptions;
    }

    private Set<File> getSourceDirectories() {
        List<String> directoryNames = isTestCompilation()
                ? this.project.getTestCompileSourceRoots()
                : this.project.getCompileSourceRoots();

        return directoryNames.stream()
                .map(File::new)
                .filter(directory -> directory.exists() && directory.isDirectory())
                .collect(Collectors.toSet());
    }

    private String buildClassPath() {
        List<String> pathElements = ExceptionUtils.runSafe(() ->
                isTestCompilation()
                        ? this.project.getTestClasspathElements()
                        : this.project.getCompileClasspathElements());

        this.pluginArtifacts.forEach(artifact -> pathElements.add(artifact.getFile().getAbsolutePath()));

        if (pathElements.isEmpty()) {
            return null;
        }

        return String.join(File.pathSeparator, pathElements);
    }

}
