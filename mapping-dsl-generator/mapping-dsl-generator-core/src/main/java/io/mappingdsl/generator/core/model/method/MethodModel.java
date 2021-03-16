package io.mappingdsl.generator.core.model.method;

import io.mappingdsl.generator.core.model.field.FieldModelBase;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Builder
public class MethodModel {

    private final FieldModelBase fieldModel;
    private final String name;
    private final String type;
    private final MethodModelType modelType;

    public String getTemplateName() {
        String methodType = StringUtils.capitalize(this.modelType.name().toLowerCase());
        return this.fieldModel.getTemplateName() + methodType;
    }

}
