package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class WrapperClassModel {

    private final String packageName;
    private final String dslClassName;
    private final String originalClassName;
    private final List<FieldModel> fieldModels = new LinkedList<>();

    @Builder
    public WrapperClassModel(String packageName, String dslClassName, String originalClassName) {
        this.packageName = packageName;
        this.dslClassName = dslClassName;
        this.originalClassName = originalClassName;
    }

    public void registerFieldModel(FieldModel field) {
        this.fieldModels.add(field);
    }

}
