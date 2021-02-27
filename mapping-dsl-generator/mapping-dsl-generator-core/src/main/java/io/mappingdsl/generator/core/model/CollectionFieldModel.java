package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CollectionFieldModel extends FieldModel {

    private final String collectionType;
    private final boolean isArray;

    @Builder(builderMethodName = "collectionFieldModelBuilder")
    public CollectionFieldModel(String name, String elementType, String boxedElementType, FieldModelType modelType,
                                String collectionType, boolean isArray, boolean isAbstract) {

        super(name, elementType, boxedElementType, modelType, isAbstract);
        this.collectionType = collectionType;
        this.isArray = isArray;
    }

    public String getElementType() {
        return getType();
    }

    public String getBoxedElementType() {
        return getBoxedType();
    }

}
