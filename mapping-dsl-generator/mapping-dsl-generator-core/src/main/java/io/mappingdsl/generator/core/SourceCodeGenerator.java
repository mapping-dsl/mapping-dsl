package io.mappingdsl.generator.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import ice.bricks.exceptions.ExceptionUtils;
import ice.bricks.io.IoUtils;
import io.mappingdsl.generator.core.model.WrapperClassModel;

import java.io.Writer;

public class SourceCodeGenerator {

    public static final SourceCodeGenerator INSTANCE = new SourceCodeGenerator();

    private final Template rootTemplate;

    private SourceCodeGenerator() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        this.rootTemplate = IoUtils.runSafe(() -> configuration.getTemplate("dsl-class.ftl"));
    }

    public void generate(WrapperClassModel dataModel, Writer writer) {
        ExceptionUtils.runSafe(() -> this.rootTemplate.process(dataModel, writer));
    }

}
