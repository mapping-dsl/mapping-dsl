package io.mappingdsl.generator.core.tests.processor;

import io.mappingdsl.generator.core.MappingDslProcessor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestAnnotationProcessor extends AbstractProcessor {

    private final List<String> scope;

    private MappingDslProcessor mappingDslProcessor;

    public TestAnnotationProcessor(List<String> scope) {
        this.scope = scope;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.mappingDslProcessor = new MappingDslProcessor(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        this.mappingDslProcessor.process(this.scope, roundEnvironment.getRootElements());
        return false;
    }

}
