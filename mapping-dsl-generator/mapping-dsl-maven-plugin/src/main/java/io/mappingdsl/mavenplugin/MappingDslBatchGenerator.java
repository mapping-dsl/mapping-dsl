package io.mappingdsl.mavenplugin;

import io.mappingdsl.generator.core.MappingDslProcessor;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(MappingDslBatchGenerator.SCOPE_OPTION)
public class MappingDslBatchGenerator extends AbstractProcessor {

    public static final String SCOPE_OPTION = "scope";

    private MappingDslProcessor mappingDslProcessor;
    private List<String> scope;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.mappingDslProcessor = new MappingDslProcessor(processingEnv);

        String scopeElements = processingEnv.getOptions().get(SCOPE_OPTION);
        this.scope = StringUtils.isNotBlank(scopeElements)
                ? Arrays.asList(scopeElements.split(","))
                : Collections.emptyList();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        this.mappingDslProcessor.process(this.scope, roundEnvironment.getRootElements());
        return false;
    }

}
