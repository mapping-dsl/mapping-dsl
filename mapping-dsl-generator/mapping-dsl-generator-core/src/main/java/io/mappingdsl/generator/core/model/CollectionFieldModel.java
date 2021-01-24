package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CollectionFieldModel extends FieldModel {

    private final String collectionType;
    private final boolean isArray;

    @Builder(builderMethodName = "collectionFieldModelBuilder")
    public CollectionFieldModel(String name, String elementType, FieldModelType modelType, String collectionType,
                                boolean isArray) {

        super(name, elementType, modelType);
        this.collectionType = collectionType;
        this.isArray = isArray;
    }

    public String getElementType() {
        return getType();
    }

}
