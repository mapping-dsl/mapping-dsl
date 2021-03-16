package io.mappingdsl.generator.core.model;

import io.mappingdsl.generator.core.model.field.FieldModelBase;
import io.mappingdsl.generator.core.model.method.MethodModel;
import io.mappingdsl.generator.core.model.property.PropertyModel;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class DslClassModel {

    private final String fullClassName;
    private final boolean isAbstract;
    private final List<FieldModelBase> fieldModels = new LinkedList<>();
    private final List<MethodModel> methodModels = new LinkedList<>();
    private final List<PropertyModel> propertyModels = new LinkedList<>();

    @Builder
    public DslClassModel(String fullClassName, boolean isAbstract) {
        this.fullClassName = fullClassName;
        this.isAbstract = isAbstract;
    }

    public void registerFieldModel(FieldModelBase field) {
        this.fieldModels.add(field);
    }

    public void registerMethodModel(MethodModel method) {
        this.methodModels.add(method);
    }

    public void registerPropertyModel(PropertyModel property) {
        this.propertyModels.add(property);
    }

}
