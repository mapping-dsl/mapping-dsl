package pojo;

import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueCollectionExpression;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ArrayFieldAccessorFunction;
import io.mappingdsl.core.expression.function.ArrayPropertyAccessorFunction;
import io.mappingdsl.core.expression.function.CollectionFieldAccessorFunction;
import io.mappingdsl.core.expression.function.CollectionPropertyAccessorFunction;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.GetMethodAccessorFunction;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.PathProcessingFunction;
import io.mappingdsl.core.expression.function.PropertyAccessorFunction;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.SetArrayMethodAccessorFunction;
import io.mappingdsl.core.expression.function.SetCollectionMethodAccessorFunction;
import io.mappingdsl.core.expression.function.SetMethodAccessorFunction;
import io.mappingdsl.core.expression.function.ValueConsumerFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public final class SimpleFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.SimpleField, FUN> {

    public static final SimpleFieldMappingDsl<pojo.SimpleField, ValueProducerFunction> $this =
            new SimpleFieldMappingDsl<>(new RootIdentityFunction("SimpleField"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "stringValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "intValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> anotherIntegerValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "anotherIntegerValue"));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> booleanValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Boolean.class, "booleanValue"));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> anotherBooleanValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Boolean.class, "anotherBooleanValue"));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueProcessingFunction> listOfString =
            new ValueCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, java.lang.String.class, "listOfString"));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueProcessingFunction> arrayOfString =
            new ValueCollectionExpression<>(this, new ArrayFieldAccessorFunction(java.lang.String.class, "arrayOfString"));

    public final ValueExpression<ROOT, java.lang.String, ValueProducerFunction> getStringValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getStringValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueConsumerFunction> setStringValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setStringValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProducerFunction> getIntValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Integer.class, "getIntValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setIntValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Integer.class, "setIntValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProducerFunction> getAnotherIntegerValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Integer.class, "getAnotherIntegerValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setAnotherIntegerValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Integer.class, "setAnotherIntegerValue"));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueProducerFunction> isBooleanValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Boolean.class, "isBooleanValue"));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueConsumerFunction> setBooleanValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Boolean.class, "setBooleanValue"));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueProducerFunction> getAnotherBooleanValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Boolean.class, "getAnotherBooleanValue"));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueConsumerFunction> setAnotherBooleanValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Boolean.class, "setAnotherBooleanValue"));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueProducerFunction> getListOfString =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getListOfString"));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueConsumerFunction> setListOfString =
            new ValueCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, java.lang.String.class, "setListOfString"));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueProducerFunction> getArrayOfString =
            new ValueCollectionExpression<>(this, new GetMethodAccessorFunction(java.lang.String[].class, "getArrayOfString"));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueConsumerFunction> setArrayOfString =
            new ValueCollectionExpression<>(this, new SetArrayMethodAccessorFunction(java.lang.String[].class, java.lang.String.class, "setArrayOfString"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getStringValue.getExpressionFunction(),
                    this.setStringValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getIntValue.getExpressionFunction(),
                    this.setIntValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> anotherIntegerValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getAnotherIntegerValue.getExpressionFunction(),
                    this.setAnotherIntegerValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> booleanValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.isBooleanValue.getExpressionFunction(),
                    this.setBooleanValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> anotherBooleanValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getAnotherBooleanValue.getExpressionFunction(),
                    this.setAnotherBooleanValue.getExpressionFunction()));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueProcessingFunction> listOfStringProperty =
            new ValueCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getListOfString.getExpressionFunction(),
                    this.setListOfString.getExpressionFunction()));

    public final ValueCollectionExpression<ROOT, java.lang.String, ValueProcessingFunction> arrayOfStringProperty =
            new ValueCollectionExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayOfString.getExpressionFunction(),
                    this.setArrayOfString.getExpressionFunction()));

    public SimpleFieldMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
