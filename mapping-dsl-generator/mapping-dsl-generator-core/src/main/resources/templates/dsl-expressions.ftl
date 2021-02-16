<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro valueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    <#switch model.abstract>
        <#case true>
            <@abstractValueField model=model />
            <#break>
        <#case false>
            <@concreteValueField model=model />
            <#break>
    </#switch>
</#macro>

<#macro abstractValueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final AbstractValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new AbstractValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro concreteValueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));</#macro>

<#macro multielementValueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    <#switch model.array>
        <#case true>
            <#switch model.abstract>
                <#case true>
                    <@abstractValuesArrayField model=model />
                    <#break>
                <#case false>
                    <@concreteValuesArrayField model=model />
                    <#break>
            </#switch>
        <#break>
        <#case false>
            <#switch model.abstract>
                <#case true>
                    <@abstractValuesCollectionField model=model />
                    <#break>
                <#case false>
                    <@concreteValuesCollectionField model=model />
                    <#break>
            </#switch>
        <#break>
    </#switch>
</#macro>

<#macro abstractValuesArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new AbstractValueArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.elementType}.class, "${model.name}"));</#macro>

<#macro concreteValuesArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ValueArrayExpression<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ValueArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.elementType}.class, "${model.name}"));</#macro>

<#macro abstractValuesCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.collectionType}, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new AbstractValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro concreteValuesCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ValueCollectionExpression<ROOT, ${model.collectionType}, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro dslField model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.type)>
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
    public final ${model.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<ROOT, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ${model.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<>(this, new ArrayFieldAccessorFunction(${model.elementType}.class, "${model.name}"));</#macro>

<#macro dslCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.CollectionFieldModel" -->
    public final ${model.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<ROOT, ${model.collectionType}, ${model.elementType}, ValueProcessingFunction> ${model.name} =
            new ${model.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<>(this, new CollectionFieldAccessorFunction(${model.collectionType}.class, ${model.elementType}.class, "${model.name}"));</#macro>

<#macro valueMethodReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.modelType>
        <#case "GETTER">
            <#switch model.fieldModel.abstract>
                <#case true>
                    <@abstractValueGetterReference model=model />
                <#break>
                <#case false>
                    <@concreteValueGetterReference model=model />
                <#break>
            </#switch>
        <#break>
        <#case "SETTER">
            <#switch model.fieldModel.abstract>
                <#case true>
                    <@abstractValueSetterReference model=model />
                <#break>
                <#case false>
                    <@concreteValueSetterReference model=model />
                <#break>
            </#switch>
        <#break>
    </#switch>
</#macro>

<#macro abstractValueGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new AbstractValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro concreteValueGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro abstractValueSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new AbstractValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro concreteValueSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro multielementValueMethodReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    <#switch model.fieldModel.array>
        <#case true>
            <#switch model.modelType>
                <#case "GETTER">
                    <#switch model.fieldModel.abstract>
                        <#case true>
                            <@abstractValueArrayGetterReference model=model />
                            <#break>
                        <#case false>
                            <@concreteValueArrayGetterReference model=model />
                            <#break>
                    </#switch>
                <#break>
                <#case "SETTER">
                    <#switch model.fieldModel.abstract>
                        <#case true>
                            <@abstractValueArraySetterReference model=model />
                            <#break>
                        <#case false>
                            <@concreteValueArraySetterReference model=model />
                            <#break>
                    </#switch>
                <#break>
            </#switch>
        <#break>
        <#case false>
            <#switch model.modelType>
                <#case "GETTER">
                    <#switch model.fieldModel.abstract>
                        <#case true>
                            <@abstractValueCollectionGetterReference model=model />
                            <#break>
                        <#case false>
                            <@concreteValueCollectionGetterReference model=model />
                            <#break>
                    </#switch>
                <#break>
                <#case "SETTER">
                    <#switch model.fieldModel.abstract>
                        <#case true>
                            <@abstractValueCollectionSetterReference model=model />
                            <#break>
                        <#case false>
                            <@concreteValueCollectionSetterReference model=model />
                            <#break>
                    </#switch>
                <#break>
            </#switch>
        <#break>
    </#switch>
</#macro>

<#macro abstractValueArrayGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new AbstractValueArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.elementType}[].class, "${model.name}"));</#macro>

<#macro concreteValueArrayGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueArrayExpression<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ValueArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.elementType}[].class, "${model.name}"));</#macro>

<#macro abstractValueArraySetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new AbstractValueArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.elementType}[].class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro concreteValueArraySetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueArrayExpression<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ValueArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.elementType}[].class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro abstractValueCollectionGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new AbstractValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));</#macro>

<#macro concreteValueCollectionGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));</#macro>

<#macro abstractValueCollectionSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new AbstractValueCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.collectionType}.class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro concreteValueCollectionSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
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
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${dslClassName}<ROOT, ValueProducerFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.type}.class, "${model.name}"));</#macro>

<#macro dslSetterReference model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.type)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${model.fieldModel.abstract?then("AbstractDslExpression", "DslExpression")}<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
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
    public final ${model.fieldModel.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<ROOT, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ${model.fieldModel.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<>(this, new GetMethodAccessorFunction(${model.fieldModel.elementType}[].class, "${model.name}"));</#macro>

<#macro dslArraySetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${model.fieldModel.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<ROOT, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ${model.fieldModel.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.elementType}[].class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro dslCollectionGetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${model.fieldModel.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueProducerFunction> ${model.name} =
            new ${model.fieldModel.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<>(this, new GetMethodAccessorFunction(${model.fieldModel.collectionType}.class, "${model.name}"));</#macro>

<#macro dslCollectionSetterReference model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${model.fieldModel.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueConsumerFunction> ${model.name} =
            new ${model.fieldModel.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.collectionType}.class, ${model.fieldModel.elementType}.class, "${model.name}"));</#macro>

<#macro valueProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    <#switch model.fieldModel.abstract>
        <#case true>
            <@abstractValueProperty model=model />
            <#break>
        <#case false>
            <@concreteValueProperty model=model />
            <#break>
    </#switch>
</#macro>

<#macro abstractValueProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractValueExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteValueProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro multielementValueProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    <#switch model.fieldModel.array>
        <#case true>
            <#switch model.fieldModel.abstract>
                <#case true>
                    <@abstractValueArrayProperty model=model />
                    <#break>
                <#case false>
                    <@concreteValueArrayProperty model=model />
                    <#break>
            </#switch>
        <#break>
        <#case false>
            <#switch model.fieldModel.abstract>
                <#case true>
                    <@abstractValueCollectionProperty model=model />
                    <#break>
                <#case false>
                    <@concreteValueCollectionProperty model=model />
                    <#break>
            </#switch>
        <#break>
    </#switch>
</#macro>

<#macro abstractValueArrayProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractValueArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteValueArrayProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueArrayExpression<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractValueCollectionProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteValueCollectionProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslProperty model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.type)>
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
    public final ${model.fieldModel.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<ROOT, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ${model.fieldModel.abstract?then("AbstractDslArrayExpression", "DslArrayExpression")}<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro dslCollectionProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ${model.fieldModel.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<ROOT, ${model.fieldModel.collectionType}, ${model.fieldModel.type}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ${model.fieldModel.abstract?then("AbstractDslCollectionExpression", "DslCollectionExpression")}<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
