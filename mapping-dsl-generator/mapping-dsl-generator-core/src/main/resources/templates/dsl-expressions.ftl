<#-- @ftlvariable name="GeneratorUtils" type="io.mappingdsl.generator.core.utils.GeneratorUtils" -->

<#macro abstractValueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final AbstractValueExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new AbstractValueExpression<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro concreteValueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractValueArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new AbstractValueArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro concreteValueArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new ValueArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractValueCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new AbstractValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteValueCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ValueCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#-- identical to concreteDslField -->
<#macro abstractDslField model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#-- identical to abstractDslField -->
<#macro concreteDslField model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.name} =
            new ${dslClassName}<>(this, new ObjectFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new AbstractDslArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslArrayField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final DslArrayExpression<ROOT, ${model.parametrizedBoxedType}, ValueProcessingFunction> ${model.name} =
            new DslArrayExpression<>(this, new ArrayFieldAccessorFunction(${model.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new AbstractDslCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteDslCollectionField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public final DslCollectionExpression<ROOT, ${model.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.name} =
            new DslCollectionExpression<>(this, new CollectionFieldAccessorFunction(${model.rawType}.class, ${model.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro abstractValueFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new AbstractValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteValueFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new ValueExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractValueFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new AbstractValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteValueFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new ValueExpression<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractValueArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new AbstractValueArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro concreteValueArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new ValueArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro abstractValueArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new AbstractValueArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteValueArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new ValueArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractValueCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new AbstractValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteValueCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractValueCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new AbstractValueCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteValueCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new ValueCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#-- identical to concreteDslFieldGetter -->
<#macro abstractDslFieldGetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${dslClassName}<ROOT, ValueProducerFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#-- identical to abstractDslFieldGetter -->
<#macro concreteDslFieldGetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final ${dslClassName}<ROOT, ValueProducerFunction> ${model.name} =
            new ${dslClassName}<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslFieldSetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractDslExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslFieldSetter model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new ${dslClassName}<>(this, new SetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new AbstractDslArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro concreteDslArrayFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProducerFunction> ${model.name} =
            new DslArrayExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}[].class, "${model.name}"));</#macro>

<#macro abstractDslArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new AbstractDslArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslArrayFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueConsumerFunction> ${model.name} =
            new DslArrayExpression<>(this, new SetArrayMethodAccessorFunction(${model.fieldModel.rawType}[].class, ${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new AbstractDslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro concreteDslCollectionFieldGetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProducerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(${model.fieldModel.rawType}.class, "${model.name}"));</#macro>

<#macro abstractDslCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new AbstractDslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro concreteDslCollectionFieldSetter model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.MethodModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueConsumerFunction> ${model.name} =
            new DslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(${model.fieldModel.rawType}.class, ${model.fieldModel.getRawTypeParameter(0)}.class, "${model.name}"));</#macro>

<#macro abstractValueFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractValueExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteValueFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractValueArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractValueArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractValueArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteValueArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractValueCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractValueCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteValueCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ValueCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new ValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#-- identical to concreteDslFieldProperty -->
<#macro abstractDslFieldProperty model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#-- identical to abstractDslFieldProperty -->
<#macro concreteDslFieldProperty model>
<#assign dslClassName = GeneratorUtils.getDslCanonicalClassName(model.fieldModel.rawType)>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final ${dslClassName}<ROOT, PathProcessingFunction> ${model.fieldModel.name}Property =
            new ${dslClassName}<>(this, new PropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractDslArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractDslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractDslArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteDslArrayFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final DslArrayExpression<ROOT, ${model.fieldModel.parametrizedBoxedType}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new DslArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro abstractDslCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final AbstractDslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new AbstractDslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>

<#macro concreteDslCollectionFieldProperty model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.PropertyModel" -->
    public final DslCollectionExpression<ROOT, ${model.fieldModel.getParametrizedBoxedTypeParameter(0)}, ValueProcessingFunction> ${model.fieldModel.name}Property =
            new DslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.${model.getterModel.name}.getExpressionFunction(),
                    this.${model.setterModel.name}.getExpressionFunction()));</#macro>
