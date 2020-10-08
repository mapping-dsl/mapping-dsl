package io.mappingdsl.generator.core;

import ice.bricks.io.IoUtils;
import io.mappingdsl.generator.core.model.FieldModel;
import io.mappingdsl.generator.core.model.FieldModelType;
import io.mappingdsl.generator.core.model.WrapperClassModel;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(GeneratorScopeProcessor.SCOPE_OPTION)
public class GeneratorScopeProcessor extends AbstractProcessor {

    public static final String SCOPE_OPTION = "scope";

    private Types typeUtils;
    private List<String> scope;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.typeUtils = processingEnv.getTypeUtils();

        String scopes = processingEnv.getOptions().get(SCOPE_OPTION);
        if (StringUtils.isNotBlank(scopes)) {
            this.scope = Arrays.asList(scopes.split(","));
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (CollectionUtils.isEmpty(this.scope)) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                    "Scope supplied for Mapping DSL Processor is empty, skipping processing.");

            return false;
        }

        for (Element element: roundEnvironment.getRootElements()) {
            if (element.getKind() == ElementKind.CLASS) {
                String className = ((TypeElement) element).getQualifiedName().toString();

                if (!GeneratorUtils.isDslWrapperClass(className)) {
                    String packageName = GeneratorUtils.getClassPackage(className);
                    String dslClassName = GeneratorUtils.getDslWrapperClassName(className);

                    WrapperClassModel wrapperClassModel = new WrapperClassModel(packageName, dslClassName, className);

                    element.getEnclosedElements().stream()
                            .filter(nestedElement -> nestedElement.getKind() == ElementKind.FIELD)
                            .map(this::buildFieldModel)
                            .forEach(wrapperClassModel::registerFieldModel);

                    String fullDslClassName = packageName + "." + dslClassName;
                    JavaFileObject fileObject = IoUtils.runSafe(() ->
                            this.processingEnv.getFiler().createSourceFile(fullDslClassName));

                    IoUtils.tryAndClose(
                            fileObject::openWriter,
                            writer -> SourceCodeGenerator.INSTANCE.generate(wrapperClassModel, writer));
                }
            }
        }

        return false;
    }

    private FieldModel buildFieldModel(Element element) {
        TypeMirror type = element.asType();
        String fieldType = type.toString();

        if (type.getKind().isPrimitive()) {
            fieldType = this.typeUtils.boxedClass((PrimitiveType) type).toString();
        }

        String fieldName = element.getSimpleName().toString();

        FieldModelType modelType = this.scope.contains(fieldType)
                ? FieldModelType.DSL_WRAPPER
                : FieldModelType.VALUE;

        return FieldModel.builder()
                .name(fieldName)
                .type(fieldType)
                .modelType(modelType)
                .build();
    }

}
