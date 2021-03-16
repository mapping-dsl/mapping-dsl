package io.mappingdsl.generator.core.model.field;

import ice.bricks.lang.model.LanguageModelUtils.TypeDetails;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
public class SimpleFieldModel extends FieldModelBase {

    SimpleFieldModel(String name, TypeDetails metadata, List<String> scope) {
        super(name, metadata, scope);
    }

    @Override
    public String getTemplateName() {
        String valueType = isAbstract() ? "abstract" : "concrete";
        String modelType = StringUtils.capitalize(getModelType().name().toLowerCase());
        return valueType + modelType + "Field";
    }

    private boolean isAbstract() {
        return this.metadata.isAbstract() || this.metadata.isInterface();
    }

    private FieldModelType getModelType() {
        return this.scope.contains(getRawType())
                ? FieldModelType.DSL
                : FieldModelType.RAW;
    }

}
