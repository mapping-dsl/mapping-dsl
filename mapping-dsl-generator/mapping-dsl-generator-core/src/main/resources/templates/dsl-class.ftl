<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->
<#-- @ftlvariable name="ClassUtils" type="ice.bricks.meta.ClassUtils.static" -->
<#-- @ftlvariable name="" type="io.mappingdsl.generator.core.model.DslClassModel" -->
<#import "dsl-expressions.ftl" as expressions>
<#assign dslClassName = GeneratorUtils.getDslClassName(fullClassName)>
package ${ClassUtils.getClassPackage(fullClassName)};

import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueCollectionExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ArrayFieldAccessorFunction;
import io.mappingdsl.core.expression.function.ArrayPropertyAccessorFunction;
import io.mappingdsl.core.expression.function.CollectionFieldAccessorFunction;
import io.mappingdsl.core.expression.function.CollectionPropertyAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.GetMethodAccessorFunction;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.PathProcessingFunction;
import io.mappingdsl.core.expression.function.PropertyAccessorFunction;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.SetArrayMethodAccessorFunction;
import io.mappingdsl.core.expression.function.SetCollectionMethodAccessorFunction;
import io.mappingdsl.core.expression.function.SetMethodAccessorFunction;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public final class ${dslClassName}<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, ${fullClassName}, FUN> {

    public static final ${dslClassName}<${fullClassName}, ValueProducerFunction> $this =
            new ${dslClassName}<>(new RootIdentityFunction("${ClassUtils.getClassName(fullClassName)}"));

    <#list fieldModels as fieldModel>
        <#switch fieldModel.modelType>
            <#case "VALUE">
                <#if GeneratorUtils.isCollectionFieldModel(fieldModel.class)>
                    <@expressions.multielementValueField model=fieldModel />
                <#else>
                    <@expressions.valueField model=fieldModel />
                </#if>
            <#break>
            <#case "DSL">
                <#if GeneratorUtils.isCollectionFieldModel(fieldModel.class)>
                    <@expressions.multielementDslField model=fieldModel />
                <#else>
                    <@expressions.dslField model=fieldModel />
                </#if>
            <#break>
        </#switch>${'\n'}
    </#list>
    <#list methodModels as methodModel>
        <#switch methodModel.fieldModel.modelType>
            <#case "VALUE">
                <#if GeneratorUtils.isCollectionFieldModel(methodModel.fieldModel.class)>
                    <@expressions.multielementValueMethodReference model=methodModel />
                <#else>
                    <@expressions.valueMethodReference model=methodModel />
                </#if>
            <#break>
            <#case "DSL">
                <#if GeneratorUtils.isCollectionFieldModel(methodModel.fieldModel.class)>
                    <@expressions.multielementDslMethodReference model=methodModel />
                <#else>
                    <@expressions.dslMethodReference model=methodModel />
                </#if>
            <#break>
        </#switch>${'\n'}
    </#list>
    <#list propertyModels as propertyModel>
        <#switch propertyModel.fieldModel.modelType>
            <#case "VALUE">
                <#if GeneratorUtils.isCollectionFieldModel(propertyModel.fieldModel.class)>
                    <@expressions.multielementValueProperty model=propertyModel />
                <#else>
                    <@expressions.valueProperty model=propertyModel />
                </#if>
            <#break>
            <#case "DSL">
                <#if GeneratorUtils.isCollectionFieldModel(propertyModel.fieldModel.class)>
                    <@expressions.multielementDslProperty model=propertyModel />
                <#else>
                    <@expressions.dslProperty model=propertyModel />
                </#if>
            <#break>
        </#switch>${'\n'}
    </#list>
    public ${dslClassName}(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private ${dslClassName}(FUN expressionFunction) {
        super(expressionFunction);
    }

}
