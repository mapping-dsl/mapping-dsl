package io.mappingdsl.generator.core.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Builder
public class MethodModel {

    private final FieldModel fieldModel;
    private final String name;
    private final String type;
    private final MethodModelType modelType;

    public String getTemplateName() {
        String methodType = StringUtils.capitalize(this.modelType.name().toLowerCase());
        return this.fieldModel.getTemplateName() + methodType;
    }

}
