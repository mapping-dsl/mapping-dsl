package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldModel {

    private final String name;
    private final String type;
    private final FieldModelType modelType;

}
