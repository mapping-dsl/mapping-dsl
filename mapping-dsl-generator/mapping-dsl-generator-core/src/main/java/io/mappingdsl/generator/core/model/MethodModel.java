package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MethodModel {

    private final FieldModel fieldModel;
    private final String name;
    private final String type;
    private final MethodModelType modelType;

}
