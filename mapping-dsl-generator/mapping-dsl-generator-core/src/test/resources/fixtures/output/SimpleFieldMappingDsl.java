package pojo;

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.GetMethodAccessorFunction;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.PathProcessingFunction;
import io.mappingdsl.core.expression.function.PropertyAccessorFunction;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.SetMethodAccessorFunction;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class SimpleFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHost<ROOT, pojo.SimpleField, FUN> {

    public static SimpleFieldMappingDsl<pojo.SimpleField, ValueProducerFunction> $this =
            new SimpleFieldMappingDsl<>(new RootIdentityFunction("SimpleField"));

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "stringValue"));

    public ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "intValue"));

    public ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> anotherIntegerValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "anotherIntegerValue"));

    public ValueExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> booleanValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Boolean.class, "booleanValue"));

    public ValueExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> anotherBooleanValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Boolean.class, "anotherBooleanValue"));

    public ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getStringValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getStringValue"));

    public ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setStringValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setStringValue"));

    public ValueExpression<ROOT, java.lang.Integer, GetMethodAccessorFunction> getIntValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Integer.class, "getIntValue"));

    public ValueExpression<ROOT, java.lang.Integer, SetMethodAccessorFunction> setIntValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Integer.class, "setIntValue"));

    public ValueExpression<ROOT, java.lang.Integer, GetMethodAccessorFunction> getAnotherIntegerValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Integer.class, "getAnotherIntegerValue"));

    public ValueExpression<ROOT, java.lang.Integer, SetMethodAccessorFunction> setAnotherIntegerValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Integer.class, "setAnotherIntegerValue"));

    public ValueExpression<ROOT, java.lang.Boolean, GetMethodAccessorFunction> isBooleanValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Boolean.class, "isBooleanValue"));

    public ValueExpression<ROOT, java.lang.Boolean, SetMethodAccessorFunction> setBooleanValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Boolean.class, "setBooleanValue"));

    public ValueExpression<ROOT, java.lang.Boolean, GetMethodAccessorFunction> getAnotherBooleanValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Boolean.class, "getAnotherBooleanValue"));

    public ValueExpression<ROOT, java.lang.Boolean, SetMethodAccessorFunction> setAnotherBooleanValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Boolean.class, "setAnotherBooleanValue"));

    public ValueExpression<ROOT, java.lang.String, PropertyAccessorFunction> stringValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getStringValue.getExpressionFunction(),
                    this.setStringValue.getExpressionFunction()));

    public ValueExpression<ROOT, java.lang.Integer, PropertyAccessorFunction> intValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getIntValue.getExpressionFunction(),
                    this.setIntValue.getExpressionFunction()));

    public ValueExpression<ROOT, java.lang.Integer, PropertyAccessorFunction> anotherIntegerValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getAnotherIntegerValue.getExpressionFunction(),
                    this.setAnotherIntegerValue.getExpressionFunction()));

    public ValueExpression<ROOT, java.lang.Boolean, PropertyAccessorFunction> booleanValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.isBooleanValue.getExpressionFunction(),
                    this.setBooleanValue.getExpressionFunction()));

    public ValueExpression<ROOT, java.lang.Boolean, PropertyAccessorFunction> anotherBooleanValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getAnotherBooleanValue.getExpressionFunction(),
                    this.setAnotherBooleanValue.getExpressionFunction()));

    public SimpleFieldMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
