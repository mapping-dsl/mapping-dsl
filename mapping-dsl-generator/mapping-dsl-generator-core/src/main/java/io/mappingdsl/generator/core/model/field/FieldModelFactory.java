package io.mappingdsl.generator.core.model.field;

import ice.bricks.lang.model.LanguageModelUtils.TypeDetails;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FieldModelFactory {

    public static FieldModelBase generateModel(String name, TypeDetails metadata, List<String> scope) {
        if (GeneratorUtils.isCollectionField(metadata.getTypeName()) || metadata.isArray()) {
            return new CollectionFieldModel(name, metadata, scope);
        }
        else {
            return new SimpleFieldModel(name, metadata, scope);
        }
    }

}
