<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro valueField model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro dslField model>
    <#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro valueMethodReference model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public ValueExpression<ROOT, ${model.fieldModel.type}, GetMethodAccessorFunction> ${model.name} =
            new ValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public ValueExpression<ROOT, ${model.fieldModel.type}, SetMethodAccessorFunction> ${model.name} =
            new ValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro dslMethodReference model>
    <#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public ${dslClassName}<ROOT, GetMethodAccessorFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public ${dslClassName}<ROOT, SetMethodAccessorFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro valueProperty model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public ValueExpression<ROOT, ${model.fieldModel.type}, PropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslProperty model>
    <#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public ${dslClassName}<ROOT, PropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
