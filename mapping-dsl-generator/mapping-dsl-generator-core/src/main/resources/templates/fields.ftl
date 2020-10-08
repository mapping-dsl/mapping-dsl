<#macro valueField model>
<#-- @ftlvariable name="model" type="io.mappingdsl.generator.core.model.FieldModel" -->
    public ValueExpression<ROOT, ${model.type}, ValueProcessingFunction> ${model.name} =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(${model.type}.class, "${model.name}"));
</#macro>
