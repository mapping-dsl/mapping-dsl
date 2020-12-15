package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CollectionFieldModel extends FieldModel {

    private final String collectionType;

    @Builder(builderMethodName = "collectionFieldModelBuilder")
    public CollectionFieldModel(String name, String elementType, String collectionType, FieldModelType modelType) {
        super(name, elementType, modelType);
        this.collectionType = collectionType;
    }

    public String getElementType() {
        return getType();
    }

}
