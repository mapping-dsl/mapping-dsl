<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->
<#-- @ftlvariable name="" type="io.mappingdsl.generator.core.model.WrapperClassModel" -->
<#import "fields.ftl" as fieldDefinitions>
<#assign dslClassName = GeneratorUtils.getDslWrapperClassName(fullClassName)>
package ${GeneratorUtils.getClassPackage(fullClassName)};

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.PathProcessingFunction;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class ${dslClassName}<ROOT, FUN extends ExpressionFunction>
        extends DslHost<ROOT, ${fullClassName}, FUN> {

    public static ${dslClassName}<${fullClassName}, ValueProducerFunction> $this =
            new ${dslClassName}<>(new RootIdentityFunction());

    <#list fieldModels as fieldModel>
        <#switch fieldModel.modelType>
            <#case "VALUE">
                <@fieldDefinitions.valueField model=fieldModel />
                <#break>
            <#case "DSL_WRAPPER">
                <@fieldDefinitions.dslWrapperField model=fieldModel />
                <#break>
        </#switch>
        <#if !fieldModel?is_last>${'\n'}</#if>
    </#list>

    public ${dslClassName}(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private ${dslClassName}(FUN expressionFunction) {
        super(expressionFunction);
    }

}
