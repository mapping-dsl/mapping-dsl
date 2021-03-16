package io.mappingdsl.generator.core.model.property;

import io.mappingdsl.generator.core.model.field.FieldModelBase;
import io.mappingdsl.generator.core.model.method.MethodModel;
import io.mappingdsl.generator.core.model.method.MethodModelType;
import lombok.Getter;
import lombok.Setter;

public class PropertyModel {

    @Getter
    private final FieldModelBase fieldModel;

    @Getter
    @Setter
    private MethodModel getterModel;

    @Getter
    @Setter
    private MethodModel setterModel;

    public PropertyModel(FieldModelBase fieldModel) {
        this.fieldModel = fieldModel;
    }

    public String getTemplateName() {
        return this.fieldModel.getTemplateName() + "Property";
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
