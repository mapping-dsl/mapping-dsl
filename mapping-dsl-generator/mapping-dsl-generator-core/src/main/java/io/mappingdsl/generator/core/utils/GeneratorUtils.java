package io.mappingdsl.generator.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratorUtils {

    private static final String DSL_CLASS_NAME_SUFFIX = "MappingDsl";

    public static String getDslWrapperClassName(String fullClassName) {
        return getClassName(fullClassName) + DSL_CLASS_NAME_SUFFIX;
    }

    public static boolean isDslWrapperClass(String fullClassName) {
        return fullClassName.endsWith(DSL_CLASS_NAME_SUFFIX);
    }

    @Nullable
    public static String getClassPackage(String fullClassName) {
        if (fullClassName.indexOf('.') >= 0) {
            return fullClassName.substring(0, fullClassName.lastIndexOf('.'));
        }

        return null;
    }

    public static String getClassName(String fullClassName) {
        if (fullClassName.indexOf('.') >= 0) {
            return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        }

        return fullClassName;
    }

}
