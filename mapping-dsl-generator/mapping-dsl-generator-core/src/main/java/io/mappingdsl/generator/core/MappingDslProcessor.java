package io.mappingdsl.generator.core;

import com.sun.tools.javac.code.Symbol;
import ice.bricks.beans.BeansUtils;
import ice.bricks.exceptions.ExceptionUtils;
import ice.bricks.io.IoUtils;
import ice.bricks.lang.model.LanguageModelUtils;
import ice.bricks.lang.model.LanguageModelUtils.TypeDetails;
import ice.bricks.meta.ClassUtils;
import io.mappingdsl.generator.core.model.CollectionFieldModel;
import io.mappingdsl.generator.core.model.DslClassModel;
import io.mappingdsl.generator.core.model.FieldModel;
import io.mappingdsl.generator.core.model.FieldModelType;
import io.mappingdsl.generator.core.model.MethodModel;
import io.mappingdsl.generator.core.model.MethodModelType;
import io.mappingdsl.generator.core.model.PropertyModel;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MappingDslProcessor {

    private final Types typeUtils;
    private final Messager messager;
    private final Filer filer;

    public MappingDslProcessor(ProcessingEnvironment processingEnvironment) {
        this.typeUtils = processingEnvironment.getTypeUtils();
        this.messager = processingEnvironment.getMessager();
        this.filer = processingEnvironment.getFiler();
    }

    public void process(List<String> scope, Set<? extends Element> elements) {
        if (CollectionUtils.isEmpty(scope)) {
            this.messager.printMessage(Diagnostic.Kind.WARNING, "No classes supplied for Mapping DSL Processor.");
            return;
        }

        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
                this.messager.printMessage(Diagnostic.Kind.WARNING, element.toString() + " is not a class, skipping.");
                continue;
            }

            TypeElement typeElement = (TypeElement) element;

            String className = typeElement.getQualifiedName().toString();

            if (!GeneratorUtils.isDslClass(className)) {
                boolean isAbstract = typeElement.getModifiers().contains(Modifier.ABSTRACT);
                DslClassModel dslClassModel = new DslClassModel(className, isAbstract);

                List<TypeElement> classHierarchy = getClassHierarchy(typeElement);

                List<Element> fields = getFields(classHierarchy);
                List<Element> methods = getMethods(classHierarchy);
                register(scope, dslClassModel, fields, methods);

                String fullDslClassName = ClassUtils.getClassPackage(className) + "." +
                        GeneratorUtils.getDslClassName(className);

                JavaFileObject fileObject = IoUtils.runSafe(() -> this.filer.createSourceFile(fullDslClassName));

                IoUtils.tryAndClose(
                        fileObject::openWriter,
                        writer -> SourceCodeGenerator.INSTANCE.generate(dslClassModel, writer));
            }
        }
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

    private void register(List<String> scope, DslClassModel model, List<Element> fields, List<Element> methods) {
        Map<Element, List<Element>> groupedFields = fields.stream()
                .collect(Collectors.groupingBy(Element::getEnclosingElement));

        Map<Element, List<Element>> groupedMethods = methods.stream()
                .collect(Collectors.groupingBy(Element::getEnclosingElement));

        groupedFields.forEach((type, typeFields) -> {
            List<Element> typeMethods = groupedMethods.getOrDefault(type, Collections.emptyList());

            typeFields.forEach(field -> {
                List<Element> relatedMethods = typeMethods.stream()
                        .filter(method -> BeansUtils.checkPropertyNamingConvention(this.typeUtils, field, method))
                        .collect(Collectors.toList());

                // register field
                FieldModel fieldModel = buildFieldModel(scope, field);
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
                String methodReturnType = LanguageModelUtils.getBoxedTypeName(this.typeUtils, field.asType());

                // check Lombok Data
                if (hostClass.getAnnotation(Data.class) != null) {
                    // getter
                    MethodModel getter = buildMethodModel(BeansUtils.getFieldGetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(getter);
                    model.registerMethodModel(getter);

                    // setter
                    MethodModel setter = buildMethodModel(BeansUtils.getFieldSetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(setter);
                    model.registerMethodModel(setter);
                }

                // check Lombok Getter
                if (hostClass.getAnnotation(Getter.class) != null || field.getAnnotation(Getter.class) != null) {
                    MethodModel getter = buildMethodModel(BeansUtils.getFieldGetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(getter);
                    model.registerMethodModel(getter);
                }

                // check Lombok Setter
                if (hostClass.getAnnotation(Setter.class) != null || field.getAnnotation(Setter.class) != null) {
                    MethodModel setter = buildMethodModel(BeansUtils.getFieldSetterName(field), methodReturnType, fieldModel);
                    propertyModel.registerMethodModel(setter);
                    model.registerMethodModel(setter);
                }

                if (propertyModel.isComplete()) {
                    model.registerPropertyModel(propertyModel);
                }
            });
        });
    }

    private FieldModel buildFieldModel(List<String> scope, Element fieldElement) {
        String fieldName = fieldElement.getSimpleName().toString();

        TypeDetails typeDetails = ExceptionUtils.defaultIfException(
                () -> LanguageModelUtils.getTypeDetails(this.typeUtils, fieldElement.asType()), null);

        String fieldTypeName = typeDetails.getTypeName();
        Class<?> fieldType = ClassUtils.getClassByName(fieldTypeName);

        if ((fieldType != null && Iterable.class.isAssignableFrom(fieldType)) || typeDetails.isArray()) {
            List<TypeDetails> generics = typeDetails.getGenerics();

            String elementTypeName = Object.class.getCanonicalName();
            String boxedElementTypeName = elementTypeName;
            boolean isAbstract = false;

            if (typeDetails.isArray()) {
                elementTypeName = fieldTypeName;
                boxedElementTypeName = typeDetails.getBoxedTypeName();
                isAbstract = typeDetails.isAbstract();
            }
            else if (generics.size() == 1) {
                TypeDetails typeGeneric = generics.get(0);
                elementTypeName = typeGeneric.getTypeName();
                boxedElementTypeName = typeGeneric.getBoxedTypeName();
                isAbstract = typeGeneric.isAbstract() || typeGeneric.isInterface();
            }

            FieldModelType modelType = scope.contains(elementTypeName)
                    ? FieldModelType.DSL
                    : FieldModelType.VALUE;

            String collectionType = typeDetails.isArray()
                    ? null
                    : fieldType.getCanonicalName();

            return CollectionFieldModel.collectionFieldModelBuilder()
                    .name(fieldName)
                    .elementType(elementTypeName)
                    .boxedElementType(boxedElementTypeName)
                    .modelType(modelType)
                    .collectionType(collectionType)
                    .isArray(typeDetails.isArray())
                    .isAbstract(isAbstract)
                    .build();
        }

        FieldModelType modelType = scope.contains(fieldTypeName)
                ? FieldModelType.DSL
                : FieldModelType.VALUE;

        return FieldModel.fieldModelBuilder()
                .name(fieldName)
                .type(fieldTypeName)
                .boxedType(typeDetails.getBoxedTypeName())
                .modelType(modelType)
                .isAbstract(typeDetails.isAbstract() || typeDetails.isInterface())
                .build();
    }

    private MethodModel buildMethodModel(Element methodElement, FieldModel fieldModel) {
        String methodName = methodElement.getSimpleName().toString();

        Symbol.MethodSymbol method = (Symbol.MethodSymbol) methodElement;
        String methodReturnType = LanguageModelUtils.getBoxedTypeName(this.typeUtils, method.getReturnType());

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

}
