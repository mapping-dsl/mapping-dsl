<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro valueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro multielementValueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    <#switch model.array>
        <#case true>
            <@valuesArrayField model=model />
        <#break>
        <#case false>
            <@valuesCollectionField model=model />
        <#break>
    </#switch>
</#macro>

<#macro valuesArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ValueCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new ArrayFieldAccessorFunction(${model.elementType}.class, "${model.name}"));</#macro>

<#macro valuesCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ValueCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro dslField model>
<#assign dslClassName = GeneratorUtils.getDslClassName(model.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro multielementDslField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    <#switch model.array>
        <#case true>
            <@dslArrayField model=model />
        <#break>
        <#case false>
            <@dslCollectionField model=model />
        <#break>
    </#switch>
</#macro>

<#macro dslArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final DslCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new DslCollectionExpression<>(this, new ArrayFieldAccessorFunction(${model.elementType}.class, "${model.name}"));</#macro>

<#macro dslCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final DslCollectionExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new DslCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro valueMethodReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
        <#case "GETTER">
            <@valueGetterReference model=model />
        <#break>
        <#case "SETTER">
            <@valueSetterReference model=model />
        <#break>
    </#switch>
</#macro>

<#macro valueGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro valueSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro multielementValueMethodReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.fieldModel.array>
        <#case true>
            <#switch model.modelType>
                <#case "GETTER">
                    <@valueArrayGetterReference model=model />
                <#break>
                <#case "SETTER">
                    <@valueArraySetterReference model=model />
                <#break>
            </#switch>
        <#break>
        <#case false>
            <#switch model.modelType>
                <#case "GETTER">
                    <@valueCollectionGetterReference model=model />
                <#break>
                <#case "SETTER">
                    <@valueCollectionSetterReference model=model />
                <#break>
            </#switch>
        <#break>
    </#switch>
</#macro>

<#macro valueArrayGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.elementType}[].class, "${model.name}"));</#macro>

<#macro valueArraySetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.elementType}[].class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro valueCollectionGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));</#macro>

<#macro valueCollectionSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.collectionType}.class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro dslMethodReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
        <#case "GETTER">
            <@dslGetterReference model=model />
        <#break>
        <#case "SETTER">
            <@dslSetterReference model=model />
        <#break>
    </#switch>
</#macro>

<#macro dslGetterReference model>
<#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro dslSetterReference model>
<#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro multielementDslMethodReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.fieldModel.array>
        <#case true>
            <#switch model.modelType>
                <#case "GETTER">
                    <@dslArrayGetterReference model=model />
                <#break>
                <#case "SETTER">
                    <@dslArraySetterReference model=model />
                <#break>
            </#switch>
        <#break>
        <#case false>
            <#switch model.modelType>
                <#case "GETTER">
                    <@dslCollectionGetterReference model=model />
                <#break>
                <#case "SETTER">
                    <@dslCollectionSetterReference model=model />
                <#break>
            </#switch>
        <#break>
    </#switch>
</#macro>

<#macro dslArrayGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.elementType}[].class, "${model.name}"));</#macro>

<#macro dslArraySetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.elementType}[].class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro dslCollectionGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));</#macro>

<#macro dslCollectionSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.collectionType}.class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro valueProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro multielementValueProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    <#switch model.fieldModel.array>
        <#case true>
            <@valueArrayProperty model=model />
        <#break>
        <#case false>
            <@valueCollectionProperty model=model />
        <#break>
    </#switch>
</#macro>

<#macro valueArrayProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueCollectionExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro valueCollectionProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslProperty model>
<#assign dslClassName = GeneratorUtils.getDslClassName(model.fieldModel.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro multielementDslProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    <#switch model.fieldModel.array>
        <#case true>
            <@dslArrayProperty model=model />
        <#break>
        <#case false>
            <@dslCollectionProperty model=model />
        <#break>
    </#switch>
</#macro>

<#macro dslArrayProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new DslCollectionExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslCollectionProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new DslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
