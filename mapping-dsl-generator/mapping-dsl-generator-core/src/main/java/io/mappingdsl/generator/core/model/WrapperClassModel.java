package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class WrapperClassModel {

    private final String fullClassName;
    private final List<FieldModel> fieldModels = new LinkedList<>();

    @Builder
    public WrapperClassModel(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public void registerFieldModel(FieldModel field) {
        this.fieldModels.add(field);
    }

}
