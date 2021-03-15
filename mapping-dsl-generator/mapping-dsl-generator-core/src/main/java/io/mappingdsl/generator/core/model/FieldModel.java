package io.mappingdsl.generator.core.model;

import ice.bricks.lang.model.LanguageModelUtils.TypeDetails;
import ice.bricks.meta.ClassUtils;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Builder
public class FieldModel {

    private final String name;
    private final TypeDetails metadata;
    private final List<String> scope;

    public String getRawType() {
        return this.metadata.getTypeName();
    }

    public String getParametrizedBoxedType() {
        return this.metadata.toBoxedString();
    }

    public String getRawTypeParameter(int index) {
        return this.metadata.getGenerics().get(index).getTypeName();
    }

    public String getParametrizedBoxedTypeParameter(int index) {
        return this.metadata.getGenerics().get(index).toBoxedString();
    }

    public String getTemplateName() {
        String valueType = isAbstract() ? "abstract" : "concrete";
        String modelType = StringUtils.capitalize(getModelType().name().toLowerCase());
        String arraySuffix = this.metadata.isArray() ? "Array" : null;
        String collectionSuffix = isCollection() ? "Collection" : null;
        return valueType + modelType + ObjectUtils.firstNonNull(arraySuffix, collectionSuffix, "") + "Field";
    }

    private boolean isAbstract() {
        if (isCollection()) {
            TypeDetails elementTypeDetails = this.metadata.getGenerics().get(0);
            return elementTypeDetails.isAbstract() || elementTypeDetails.isInterface();
        }
        else {
            return this.metadata.isAbstract() || this.metadata.isInterface();
        }
    }

    private boolean isCollection() {
        Class<?> fieldType = ClassUtils.getClassByName(getRawType());
        return fieldType != null && Iterable.class.isAssignableFrom(fieldType);
    }

    private FieldModelType getModelType() {
        String type = isCollection() ? getRawTypeParameter(0) : getRawType();
        return this.scope.contains(type)
                ? FieldModelType.DSL
                : FieldModelType.RAW;
    }

}
