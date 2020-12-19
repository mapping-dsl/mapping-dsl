package io.mappingdsl.mavenplugin.test.lombok;

import ice.bricks.meta.ClassUtils;
import io.mappingdsl.generator.core.utils.GeneratorUtils;
import io.mappingdsl.mavenplugin.test.lombok.fixtures.SimpleDataPojo;
import io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class LombokSupportTest {

    @Test
    void shouldGenerateDslClass() {
        String testClassName = SimplePojo.class.getCanonicalName();
        File generatedDsl = getGeneratedFile(testClassName);
        File expectedDsl = getExpectedFile(testClassName);
        assertThat(generatedDsl).hasSameTextualContentAs(expectedDsl);

        testClassName = SimpleDataPojo.class.getCanonicalName();
        generatedDsl = getGeneratedFile(testClassName);
        expectedDsl = getExpectedFile(testClassName);
        assertThat(generatedDsl).hasSameTextualContentAs(expectedDsl);
    }

    private File getGeneratedFile(String testClassName) {
        return new File(String.join(File.separator,
                "target/generated-test-sources/java",
                ClassUtils.getClassPackage(testClassName).replace(".", File.separator),
                GeneratorUtils.getDslClassName(testClassName) + ".java"
        ));
    }

    private File getExpectedFile(String testClassName) {
        return new File(String.join(File.separator,
                "src/test/resources",
                GeneratorUtils.getDslClassName(testClassName) + ".java"));
    }


}
