<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->
<#-- @ftlvariable name="ClassUtils" type="ice.bricks.meta.ClassUtils.static" -->
<#-- @ftlvariable name="" type="io.mappingdsl.generator.core.model.DslClassModel" -->
<#include "dsl-expressions.ftl">
<#assign rootDslClassName = GeneratorUtils.getDslClassName(fullClassName)>
package ${ClassUtils.getClassPackage(fullClassName)};

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.AbstractValueArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.array.ValueArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.AbstractValueCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.collection.ValueCollectionExpression;
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
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.AbstractValueExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import io.mappingdsl.core.expression.simple.ValueExpression;

import javax.annotation.Generated;

@Generated("MappingDsl")
public final class ${rootDslClassName}<ROOT, FUN extends ExpressionFunction>
        extends ${abstract?then("AbstractDslExpression", "DslExpression")}<ROOT, ${fullClassName}, FUN> {

    public static final ${rootDslClassName}<${fullClassName}, ValueProducerFunction> $this =
            new ${rootDslClassName}<>(new RootIdentityFunction("${ClassUtils.getClassName(fullClassName)}"));

    <#list fieldModels as fieldModel>
        <#assign templateName=fieldModel.getTemplateName() />
        <#lt><@.vars[templateName] model=fieldModel />
        <#lt><#if fieldModel?has_next>${'\n'}</#if>
    </#list>

    <#list methodModels as methodModel>
        <#assign templateName=methodModel.getTemplateName() />
        <#lt><@.vars[templateName] model=methodModel />
        <#lt><#if methodModel?has_next>${'\n'}</#if>
    </#list>

    <#list propertyModels as propertyModel>
        <#assign templateName=propertyModel.getTemplateName() />
        <#lt><@.vars[templateName] model=propertyModel />
        <#lt><#if propertyModel?has_next>${'\n'}</#if>
    </#list>

    public ${rootDslClassName}(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private ${rootDslClassName}(FUN expressionFunction) {
        super(expressionFunction);
    }

}
