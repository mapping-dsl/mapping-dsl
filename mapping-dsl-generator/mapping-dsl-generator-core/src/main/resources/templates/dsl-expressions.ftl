<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro valueField model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro valuesCollectionField model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ValueCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro dslField model>
    <#assign dslClassName = GeneratorUtils.getDslClassName(model.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro dslCollectionField model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final DslCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new DslCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

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

<#macro valueCollectionMethodReference model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, GetMethodAccessorFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, SetCollectionMethodAccessorFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.collectionType}.class, ${model.fieldModel.elementType}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro dslMethodReference model>
    <#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public final DslExpression<ROOT, ${model.fieldModel.type}, GetMethodAccessorFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public final DslExpression<ROOT, ${model.fieldModel.type}, SetMethodAccessorFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro dslCollectionMethodReference model>
    <#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
    <#case "GETTER">
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, GetMethodAccessorFunction> ${model.name} =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));<#break>
    <#case "SETTER">
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, SetCollectionMethodAccessorFunction> ${model.name} =
            new DslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.collectionType}.class, ${model.fieldModel.elementType}.class, "${model.name}"));<#break>
    </#switch>
</#macro>

<#macro valueProperty model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, PropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro valueCollectionProperty model>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, CollectionPropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslProperty model>
    <#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ${dslClassName}<ROOT, PropertyAccessorFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslCollectionProperty model>
    <#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
    <#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, CollectionPropertyAccessorFunction> ${model.fieldModel.name}Property =
            new DslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
