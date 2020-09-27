package io.mappingdsl.generator.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratorUtils {

    private static final String DSL_CLASS_NAME_SUFFIX = "MappingDsl";

    public static String getDslWrapperClassName(String originalClassName) {
        return originalClassName + DSL_CLASS_NAME_SUFFIX;
    }

}
