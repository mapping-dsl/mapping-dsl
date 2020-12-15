<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro valueField model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro valuesCollectionField model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ValuesCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ValuesCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro dslField model>
    <#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro valueMethodReference model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public final ValueExpression<ROOT, ${model.fieldModel.type}, GetMethodAccessorFunction> ${model.name} =
            new ValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public final ValueExpression<ROOT, ${model.fieldModel.type}, SetMethodAccessorFunction> ${model.name} =
            new ValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro dslMethodReference model>
    <#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public final DslHostExpression<ROOT, ${model.fieldModel.type}, GetMethodAccessorFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public final DslHostExpression<ROOT, ${model.fieldModel.type}, SetMethodAccessorFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro valueProperty model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, PropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslProperty model>
    <#assign dslClassName = GeneratorUtils.getDslWrapperClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ${dslClassName}<ROOT, PropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
