<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro abstractRawField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final AbstractRawExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new AbstractRawExpression<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro concreteRawField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final RawExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractRawArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final AbstractRawArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new AbstractRawArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro concreteRawArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final RawArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new RawArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractRawCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final AbstractRawCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new AbstractRawCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteRawCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final RawCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new RawCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#-- identical to concreteDslField -->
<#macro abstractDslField model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#-- identical to abstractDslField -->
<#macro concreteDslField model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final AbstractDslArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new AbstractDslArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final DslArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new DslArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new AbstractDslCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteDslCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.field.FieldModelBase" -->
    public final DslCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new DslCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro abstractRawFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractRawExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new AbstractRawExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteRawFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final RawExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new RawExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractRawFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractRawExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new AbstractRawExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteRawFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final RawExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new RawExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractRawArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractRawArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new AbstractRawArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro concreteRawArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final RawArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new RawArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro abstractRawArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractRawArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new AbstractRawArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteRawArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final RawArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new RawArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractRawCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractRawCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new AbstractRawCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteRawCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final RawCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new RawCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractRawCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractRawCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new AbstractRawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteRawCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final RawCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new RawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#-- identical to concreteDslFieldGetter -->
<#macro abstractDslFieldGetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final ${dslClassName}<ROOT, ValueProducerFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#-- identical to abstractDslFieldGetter -->
<#macro concreteDslFieldGetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final ${dslClassName}<ROOT, ValueProducerFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslFieldSetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractDslExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslFieldSetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final DslExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new AbstractDslArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro concreteDslArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final DslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new DslArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro abstractDslArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new AbstractDslArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final DslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new DslArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new AbstractDslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new AbstractDslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteDslCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.method.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro abstractRawFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final AbstractRawExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractRawExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteRawFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final RawExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractRawArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final AbstractRawArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractRawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteRawArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final RawArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new RawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractRawCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final AbstractRawCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractRawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteRawCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final RawCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new RawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#-- identical to concreteDslFieldProperty -->
<#macro abstractDslFieldProperty model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#-- identical to abstractDslFieldProperty -->
<#macro concreteDslFieldProperty model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractDslArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractDslArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteDslArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final DslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new DslArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractDslCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractDslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteDslCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.property.PropertyModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new DslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
