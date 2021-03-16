package io.mappingdsl.generator.core.model.field;

import ice.bricks.lang.model.LanguageModelUtils.TypeDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class FieldModelBase {

    protected final String name;
    protected final TypeDetails metadata;
    protected final List<String> scope;

    public String getRawType() {
        return this.metadata.getTypeName();
    }

    public String getParametrizedBoxedType() {
        return this.metadata.toBoxedString();
    }

    public String getRawTypeParameter(int index) {
        return this.metadata.getGenerics().get(index).getTypeName();
    }

    public String getParametrizedBoxedTypeParameter(int index) {
        return this.metadata.getGenerics().get(index).toBoxedString();
    }

    public abstract String getTemplateName();

}
