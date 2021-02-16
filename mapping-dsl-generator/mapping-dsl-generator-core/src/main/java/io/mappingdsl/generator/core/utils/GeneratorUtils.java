package io.mappingdsl.generator.core.utils;

import ice.bricks.meta.ClassUtils;
import io.mappingdsl.generator.core.model.CollectionFieldModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratorUtils {

    private static final String DSL_CLASS_NAME_SUFFIX = "MappingDsl";

    public static String getDslClassName(String fullClassName) {
        return ClassUtils.getClassName(fullClassName) + DSL_CLASS_NAME_SUFFIX;
    }

    public static String getDslCanonicalClassName(String fullClassName) {
        return fullClassName + DSL_CLASS_NAME_SUFFIX;
    }

    public static boolean isDslClass(String fullClassName) {
        return fullClassName.endsWith(DSL_CLASS_NAME_SUFFIX);
    }

    public static boolean isCollectionFieldModel(Class<?> fieldModelType) {
        return CollectionFieldModel.class.getCanonicalName().equals(fieldModelType.getCanonicalName());
    }

}
