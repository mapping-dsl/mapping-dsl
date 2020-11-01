<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro valueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro dslWrapperField model>
<#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>
