package io.mappingdsl.generator.core;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import ice.bricks.io.IoUtils;
import ice.bricks.meta.ClassUtils;
import io.mappingdsl.generator.core.model.FieldModel;
import io.mappingdsl.generator.core.model.FieldModelType;
import io.mappingdsl.generator.core.model.MethodModel;
import io.mappingdsl.generator.core.model.MethodModelType;
import io.mappingdsl.generator.core.model.PropertyModel;
import io.mappingdsl.generator.core.model.WrapperClassModel;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
                TypeElement typeElement = (TypeElement) element;

                String className = typeElement.getQualifiedName().toString();

                if (!GeneratorUtils.isDslWrapperClass(className)) {
                    WrapperClassModel wrapperClassModel = new WrapperClassModel(className);

                    List<TypeElement> classHierarchy = getClassHierarchy(typeElement);

                    List<Element> fields = getFields(classHierarchy);
                    List<Element> methods = getMethods(classHierarchy);
                    register(wrapperClassModel, fields, methods);

                    String fullDslClassName = ClassUtils.getClassPackage(className) + "." +
                            GeneratorUtils.getDslWrapperClassName(className);

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

    private List<TypeElement> getClassHierarchy(TypeElement typeElement) {
        List<TypeElement> classHierarchy = new LinkedList<>();

        // stop hierarchy at Object class
        while (!typeElement.getQualifiedName().toString().equals(Object.class.getCanonicalName())) {
            classHierarchy.add(typeElement);
            typeElement = (TypeElement) this.typeUtils.asElement(typeElement.getSuperclass());
        }
        return classHierarchy;
    }

    private List<Element> getFields(List<TypeElement> classHierarchy) {
        return classHierarchy.stream()
                .flatMap(type -> type.getEnclosedElements().stream())
                .filter(nestedElement -> nestedElement.getKind() == ElementKind.FIELD)
                .collect(Collectors.toList());
    }

    private List<Element> getMethods(List<TypeElement> classHierarchy) {
        return classHierarchy.stream()
                .flatMap(type -> type.getEnclosedElements().stream())
                .filter(nestedElement -> nestedElement.getKind() == ElementKind.METHOD)
                .collect(Collectors.toList());
    }

    private void register(WrapperClassModel model, List<Element> fields, List<Element> methods) {
        Map<Element, List<Element>> groupedFields = fields.stream()
                .collect(Collectors.groupingBy(Element::getEnclosingElement));

        Map<Element, List<Element>> groupedMethods = methods.stream()
                .collect(Collectors.groupingBy(Element::getEnclosingElement));

        groupedFields.forEach((type, typeFields) -> {
            List<Element> typeMethods = groupedMethods.getOrDefault(type, Collections.emptyList());

            typeFields.forEach(field -> {
                List<Element> relatedMethods = typeMethods.stream()
                        .filter(typeMethod -> checkPropertyNamingConvention(field, typeMethod))
                        .collect(Collectors.toList());

                // register field
                FieldModel fieldModel = buildFieldModel(field);
                model.registerFieldModel(fieldModel);

                // register property
                PropertyModel propertyModel = new PropertyModel(fieldModel);

                // check existing methods first
                relatedMethods.forEach(method -> {
                    MethodModel methodModel = buildMethodModel(method, fieldModel);
                    propertyModel.registerMethodModel(methodModel);
                    model.registerMethodModel(methodModel);
                });

                Element hostClass = field.getEnclosingElement();
                String methodReturnType = getBoxedTypeName(field.asType());

                // check Lombok Data
                if (hostClass.getAnnotation(Data.class) != null) {
                    // getter
                    MethodModel getter = buildMethodModel(buildFieldGetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(getter);
                    model.registerMethodModel(getter);

                    // setter
                    MethodModel setter = buildMethodModel(buildFieldSetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(setter);
                    model.registerMethodModel(setter);
                }

                // check Lombok Getter
                if (hostClass.getAnnotation(Getter.class) != null || field.getAnnotation(Getter.class) != null) {
                    MethodModel getter = buildMethodModel(buildFieldGetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(getter);
                    model.registerMethodModel(getter);
                }

                // check Lombok Setter
                if (hostClass.getAnnotation(Setter.class) != null || field.getAnnotation(Setter.class) != null) {
                    MethodModel setter = buildMethodModel(buildFieldSetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(setter);
                    model.registerMethodModel(setter);
                }

                if (propertyModel.isComplete()) {
                    model.registerPropertyModel(propertyModel);
                }
            });
        });
    }

    private FieldModel buildFieldModel(Element fieldElement) {
        String fieldType = getBoxedTypeName(fieldElement.asType());
        String fieldName = fieldElement.getSimpleName().toString();

        FieldModelType modelType = this.scope.contains(fieldType)
                ? FieldModelType.DSL
                : FieldModelType.VALUE;

        return FieldModel.builder()
                .name(fieldName)
                .type(fieldType)
                .modelType(modelType)
                .build();
    }

    private MethodModel buildMethodModel(Element methodElement, FieldModel fieldModel) {
        String methodName = methodElement.getSimpleName().toString();

        Symbol.MethodSymbol method = (Symbol.MethodSymbol) methodElement;
        String methodReturnType = getBoxedTypeName(method.getReturnType());

        return buildMethodModel(methodName, methodReturnType, fieldModel);
    }

    private MethodModel buildMethodModel(String methodName, String methodReturnType, FieldModel fieldModel) {
        MethodModelType modelType = methodName.startsWith("set")
                ? MethodModelType.SETTER
                : MethodModelType.GETTER;

        return MethodModel.builder()
                .fieldModel(fieldModel)
                .name(methodName)
                .type(methodReturnType)
                .modelType(modelType)
                .build();
    }

    private boolean checkPropertyNamingConvention(Element fieldElement, Element methodElement) {
        String fieldName = StringUtils.capitalize(fieldElement.getSimpleName().toString());

        Symbol.MethodSymbol method = (Symbol.MethodSymbol) methodElement;
        String methodName = method.getSimpleName().toString();

        if (((isBooleanType(method.getReturnType()) && methodName.equals("is" + fieldName))
                || methodName.equals("get" + fieldName)) && method.getParameters().isEmpty()) {

            return fieldElement.asType().equals(method.getReturnType());
        }

        if (methodName.equals("set" + fieldName) && method.getParameters().size() == 1) {
            return fieldElement.asType().equals(method.getParameters().get(0).asType());
        }

        return false;
    }

    private boolean isBooleanType(Type type) {
        String typeName = getBoxedTypeName(type);
        return Boolean.class.getCanonicalName().equals(typeName);
    }

    private String buildFieldGetterName(Element field) {
        String fieldName = StringUtils.capitalize(field.getSimpleName().toString());
        String methodName = "get" + fieldName;
        if (field.asType().getKind().isPrimitive() && field.asType().getKind() == TypeKind.BOOLEAN) {
            methodName = "is" + fieldName;
        }

        return methodName;
    }

    private String buildFieldSetterName(Element field) {
        String fieldName = StringUtils.capitalize(field.getSimpleName().toString());
        return "set" + fieldName;
    }

    private String getBoxedTypeName(TypeMirror type) {
        String typeName = type.toString();

        if (type.getKind().isPrimitive()) {
            typeName = this.typeUtils.boxedClass((PrimitiveType) type).toString();
        }

        return typeName;
    }

}
