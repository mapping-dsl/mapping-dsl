package io.mappingdsl.generator.core.model;

import lombok.Getter;
import lombok.Setter;

public class PropertyModel {

    @Getter
    private final FieldModel fieldModel;

    @Getter
    @Setter
    private MethodModel getterModel;

    @Getter
    @Setter
    private MethodModel setterModel;

    public PropertyModel(FieldModel fieldModel) {
        this.fieldModel = fieldModel;
    }

    public void registerMethodModel(MethodModel methodModel) {
        if (methodModel.getModelType() == MethodModelType.GETTER) {
            this.getterModel = methodModel;
        }

        if (methodModel.getModelType() == MethodModelType.SETTER) {
            this.setterModel = methodModel;
        }
    }

    public boolean isComplete() {
        return this.getterModel != null && this.setterModel != null;
    }

}
