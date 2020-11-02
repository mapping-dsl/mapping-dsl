package io.mappingdsl.generator.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratorUtils {

    private static final String DSL_CLASS_NAME_SUFFIX = "MappingDsl";

    public static String getDslWrapperClassName(String fullClassName) {
        String normalizedClassName = normalizeClassName(fullClassName);

        if (normalizedClassName.indexOf('.') >= 0) {
            return normalizedClassName.substring(normalizedClassName.lastIndexOf('.') + 1) + DSL_CLASS_NAME_SUFFIX;
        }

        return fullClassName + DSL_CLASS_NAME_SUFFIX;
    }

    public static boolean isDslWrapperClass(String fullClassName) {
        return fullClassName.endsWith(DSL_CLASS_NAME_SUFFIX);
    }

    @Nullable
    public static String getClassPackage(String fullClassName) {
        String normalizedClassName = normalizeClassName(fullClassName);

        if (normalizedClassName.indexOf('.') >= 0) {
            return normalizedClassName.substring(0, normalizedClassName.lastIndexOf('.'));
        }

        return null;
    }

    public static String getClassName(String fullClassName) {
        String normalizedClassName = normalizeClassName(fullClassName);

        if (normalizedClassName.indexOf('.') >= 0) {
            return normalizedClassName.substring(normalizedClassName.lastIndexOf('.') + 1);
        }

        return fullClassName;
    }

    private static String normalizeClassName(String className) {
        // align filesystem path with regular package naming
        return className.replace('/', '.');
    }

}
