package io.mappingdsl.generator.core;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import ice.bricks.exceptions.ExceptionUtils;
import ice.bricks.io.IoUtils;
import io.mappingdsl.generator.core.model.WrapperClassModel;
import io.mappingdsl.generator.core.utils.GeneratorUtils;

import java.io.Writer;

public class SourceCodeGenerator {

    public static final SourceCodeGenerator INSTANCE = new SourceCodeGenerator();

    private final Template rootTemplate;

    private SourceCodeGenerator() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        BeansWrapper beansWrapper = new BeansWrapper(Configuration.VERSION_2_3_30);
        TemplateModel templateModel = ExceptionUtils.runSafe(
                () -> beansWrapper.getStaticModels().get(GeneratorUtils.class.getName()));
        configuration.setSharedVariable("GeneratorUtils", templateModel);

        this.rootTemplate = IoUtils.runSafe(() -> configuration.getTemplate("dsl-class.ftl"));
    }

    public void generate(WrapperClassModel dataModel, Writer writer) {
        ExceptionUtils.runSafe(() -> this.rootTemplate.process(dataModel, writer));
    }

}
