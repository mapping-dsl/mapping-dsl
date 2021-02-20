package io.mappingdsl.annotation;

import io.mappingdsl.generator.core.MappingDslProcessor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("io.mappingdsl.annotation.MappingDsl")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MappingDslAnnotationProcessor extends AbstractProcessor {

    private MappingDslProcessor mappingDslProcessor;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.mappingDslProcessor = new MappingDslProcessor(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(MappingDsl.class);
        List<String> scope = elements.stream().map(Element::toString).collect(Collectors.toList());

        this.mappingDslProcessor.process(scope, elements);

        return true;
    }

}
