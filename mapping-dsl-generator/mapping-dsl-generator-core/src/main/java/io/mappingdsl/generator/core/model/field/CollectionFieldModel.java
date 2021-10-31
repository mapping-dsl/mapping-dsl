package io.mappingdsl.generator.core.model.field;

import ice.bricks.lang.model.TypeDetails;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
public class CollectionFieldModel extends FieldModelBase {

    CollectionFieldModel(String name, TypeDetails metadata, List<String> scope) {
        super(name, metadata, scope);
    }

    @Override
    public String getTemplateName() {
        String valueType = isAbstract() ? "abstract" : "concrete";
        String modelType = StringUtils.capitalize(getModelType().name().toLowerCase());
        String arraySuffix = this.metadata.isArray() ? "Array" : null;
        String collectionSuffix = GeneratorUtils.isCollectionField(getRawType()) ? "Collection" : null;
        return valueType + modelType + ObjectUtils.firstNonNull(arraySuffix, collectionSuffix) + "Field";
    }

    private boolean isAbstract() {
        TypeDetails elementTypeDetails = this.metadata.isArray()
                ? this.metadata
                : this.metadata.getGenerics().get(0);

        return elementTypeDetails.isAbstract() || elementTypeDetails.isInterface();
    }

    private FieldModelType getModelType() {
        String type = this.metadata.isArray()
                ? getRawType()
                : getRawTypeParameter(0);

        return this.scope.contains(type)
                ? FieldModelType.DSL
                : FieldModelType.RAW;
    }

}
