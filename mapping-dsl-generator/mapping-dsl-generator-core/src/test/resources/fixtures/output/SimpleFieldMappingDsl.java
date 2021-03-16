package pojo;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.AbstractRawArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.array.RawArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.AbstractRawCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.collection.RawCollectionExpression;
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
import io.mappingdsl.core.expression.simple.AbstractDslExpression;
import io.mappingdsl.core.expression.simple.AbstractRawExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import io.mappingdsl.core.expression.simple.RawExpression;

import javax.annotation.Generated;

@Generated("MappingDsl")
public final class SimpleFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.SimpleField, FUN> {

    public static final SimpleFieldMappingDsl<pojo.SimpleField, ValueProducerFunction> $this =
            new SimpleFieldMappingDsl<>(new RootIdentityFunction("SimpleField"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "stringValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(int.class, "intValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueProcessingFunction> anotherIntegerValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "anotherIntegerValue"));

    public final RawExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> booleanValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(boolean.class, "booleanValue"));

    public final RawExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> anotherBooleanValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Boolean.class, "anotherBooleanValue"));

    public final RawCollectionExpression<ROOT, java.lang.String, ValueProcessingFunction> listOfString =
            new RawCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, java.lang.String.class, "listOfString"));

    public final RawArrayExpression<ROOT, java.lang.Integer, ValueProcessingFunction> arrayOfPrimitiveInt =
            new RawArrayExpression<>(this, new ArrayFieldAccessorFunction(int.class, "arrayOfPrimitiveInt"));

    public final RawArrayExpression<ROOT, java.lang.String, ValueProcessingFunction> arrayOfString =
            new RawArrayExpression<>(this, new ArrayFieldAccessorFunction(java.lang.String.class, "arrayOfString"));

    public final AbstractRawExpression<ROOT, java.lang.Number, ValueProcessingFunction> abstractNumberValue =
            new AbstractRawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Number.class, "abstractNumberValue"));

    public final AbstractRawArrayExpression<ROOT, java.lang.Number, ValueProcessingFunction> arrayOfAbstractNumberValue =
            new AbstractRawArrayExpression<>(this, new ArrayFieldAccessorFunction(java.lang.Number.class, "arrayOfAbstractNumberValue"));

    public final AbstractRawCollectionExpression<ROOT, java.lang.Number, ValueProcessingFunction> listOfAbstractNumberValue =
            new AbstractRawCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, java.lang.Number.class, "listOfAbstractNumberValue"));

    public final AbstractRawExpression<ROOT, java.lang.CharSequence, ValueProcessingFunction> interfaceValue =
            new AbstractRawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.CharSequence.class, "interfaceValue"));

    public final AbstractRawArrayExpression<ROOT, java.lang.CharSequence, ValueProcessingFunction> arrayOfInterfaceValue =
            new AbstractRawArrayExpression<>(this, new ArrayFieldAccessorFunction(java.lang.CharSequence.class, "arrayOfInterfaceValue"));

    public final AbstractRawCollectionExpression<ROOT, java.lang.CharSequence, ValueProcessingFunction> listOfInterfaceValue =
            new AbstractRawCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, java.lang.CharSequence.class, "listOfInterfaceValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProducerFunction> getStringValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getStringValue"));

    public final RawExpression<ROOT, java.lang.String, ValueConsumerFunction> setStringValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setStringValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueProducerFunction> getIntValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(int.class, "getIntValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setIntValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(int.class, "setIntValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueProducerFunction> getAnotherIntegerValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.Integer.class, "getAnotherIntegerValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setAnotherIntegerValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.Integer.class, "setAnotherIntegerValue"));

    public final RawExpression<ROOT, java.lang.Boolean, ValueProducerFunction> isBooleanValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(boolean.class, "isBooleanValue"));

    public final RawExpression<ROOT, java.lang.Boolean, ValueConsumerFunction> setBooleanValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(boolean.class, "setBooleanValue"));

    public final RawExpression<ROOT, java.lang.Boolean, ValueProducerFunction> getAnotherBooleanValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.Boolean.class, "getAnotherBooleanValue"));

    public final RawExpression<ROOT, java.lang.Boolean, ValueConsumerFunction> setAnotherBooleanValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.Boolean.class, "setAnotherBooleanValue"));

    public final RawCollectionExpression<ROOT, java.lang.String, ValueProducerFunction> getListOfString =
            new RawCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getListOfString"));

    public final RawCollectionExpression<ROOT, java.lang.String, ValueConsumerFunction> setListOfString =
            new RawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, java.lang.String.class, "setListOfString"));

    public final RawArrayExpression<ROOT, java.lang.Integer, ValueProducerFunction> getArrayOfPrimitiveInt =
            new RawArrayExpression<>(this, new GetMethodAccessorFunction(int[].class, "getArrayOfPrimitiveInt"));

    public final RawArrayExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setArrayOfPrimitiveInt =
            new RawArrayExpression<>(this, new SetArrayMethodAccessorFunction(int[].class, int.class, "setArrayOfPrimitiveInt"));

    public final RawArrayExpression<ROOT, java.lang.String, ValueProducerFunction> getArrayOfString =
            new RawArrayExpression<>(this, new GetMethodAccessorFunction(java.lang.String[].class, "getArrayOfString"));

    public final RawArrayExpression<ROOT, java.lang.String, ValueConsumerFunction> setArrayOfString =
            new RawArrayExpression<>(this, new SetArrayMethodAccessorFunction(java.lang.String[].class, java.lang.String.class, "setArrayOfString"));

    public final AbstractRawExpression<ROOT, java.lang.Number, ValueProducerFunction> getAbstractNumberValue =
            new AbstractRawExpression<>(this, new GetMethodAccessorFunction(java.lang.Number.class, "getAbstractNumberValue"));

    public final AbstractRawExpression<ROOT, java.lang.Number, ValueConsumerFunction> setAbstractNumberValue =
            new AbstractRawExpression<>(this, new SetMethodAccessorFunction(java.lang.Number.class, "setAbstractNumberValue"));

    public final AbstractRawArrayExpression<ROOT, java.lang.Number, ValueProducerFunction> getArrayOfAbstractNumberValue =
            new AbstractRawArrayExpression<>(this, new GetMethodAccessorFunction(java.lang.Number[].class, "getArrayOfAbstractNumberValue"));

    public final AbstractRawArrayExpression<ROOT, java.lang.Number, ValueConsumerFunction> setArrayOfAbstractNumberValue =
            new AbstractRawArrayExpression<>(this, new SetArrayMethodAccessorFunction(java.lang.Number[].class, java.lang.Number.class, "setArrayOfAbstractNumberValue"));

    public final AbstractRawCollectionExpression<ROOT, java.lang.Number, ValueProducerFunction> getListOfAbstractNumberValue =
            new AbstractRawCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getListOfAbstractNumberValue"));

    public final AbstractRawCollectionExpression<ROOT, java.lang.Number, ValueConsumerFunction> setListOfAbstractNumberValue =
            new AbstractRawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, java.lang.Number.class, "setListOfAbstractNumberValue"));

    public final AbstractRawExpression<ROOT, java.lang.CharSequence, ValueProducerFunction> getInterfaceValue =
            new AbstractRawExpression<>(this, new GetMethodAccessorFunction(java.lang.CharSequence.class, "getInterfaceValue"));

    public final AbstractRawExpression<ROOT, java.lang.CharSequence, ValueConsumerFunction> setInterfaceValue =
            new AbstractRawExpression<>(this, new SetMethodAccessorFunction(java.lang.CharSequence.class, "setInterfaceValue"));

    public final AbstractRawArrayExpression<ROOT, java.lang.CharSequence, ValueProducerFunction> getArrayOfInterfaceValue =
            new AbstractRawArrayExpression<>(this, new GetMethodAccessorFunction(java.lang.CharSequence[].class, "getArrayOfInterfaceValue"));

    public final AbstractRawArrayExpression<ROOT, java.lang.CharSequence, ValueConsumerFunction> setArrayOfInterfaceValue =
            new AbstractRawArrayExpression<>(this, new SetArrayMethodAccessorFunction(java.lang.CharSequence[].class, java.lang.CharSequence.class, "setArrayOfInterfaceValue"));

    public final AbstractRawCollectionExpression<ROOT, java.lang.CharSequence, ValueProducerFunction> getListOfInterfaceValue =
            new AbstractRawCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getListOfInterfaceValue"));

    public final AbstractRawCollectionExpression<ROOT, java.lang.CharSequence, ValueConsumerFunction> setListOfInterfaceValue =
            new AbstractRawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, java.lang.CharSequence.class, "setListOfInterfaceValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getStringValue.getExpressionFunction(),
                    this.setStringValue.getExpressionFunction()));

    public final RawExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getIntValue.getExpressionFunction(),
                    this.setIntValue.getExpressionFunction()));

    public final RawExpression<ROOT, java.lang.Integer, ValueProcessingFunction> anotherIntegerValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getAnotherIntegerValue.getExpressionFunction(),
                    this.setAnotherIntegerValue.getExpressionFunction()));

    public final RawExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> booleanValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.isBooleanValue.getExpressionFunction(),
                    this.setBooleanValue.getExpressionFunction()));

    public final RawExpression<ROOT, java.lang.Boolean, ValueProcessingFunction> anotherBooleanValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getAnotherBooleanValue.getExpressionFunction(),
                    this.setAnotherBooleanValue.getExpressionFunction()));

    public final RawCollectionExpression<ROOT, java.lang.String, ValueProcessingFunction> listOfStringProperty =
            new RawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getListOfString.getExpressionFunction(),
                    this.setListOfString.getExpressionFunction()));

    public final RawArrayExpression<ROOT, java.lang.Integer, ValueProcessingFunction> arrayOfPrimitiveIntProperty =
            new RawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayOfPrimitiveInt.getExpressionFunction(),
                    this.setArrayOfPrimitiveInt.getExpressionFunction()));

    public final RawArrayExpression<ROOT, java.lang.String, ValueProcessingFunction> arrayOfStringProperty =
            new RawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayOfString.getExpressionFunction(),
                    this.setArrayOfString.getExpressionFunction()));

    public final AbstractRawExpression<ROOT, java.lang.Number, ValueProcessingFunction> abstractNumberValueProperty =
            new AbstractRawExpression<>(this, new PropertyAccessorFunction(
                    this.getAbstractNumberValue.getExpressionFunction(),
                    this.setAbstractNumberValue.getExpressionFunction()));

    public final AbstractRawArrayExpression<ROOT, java.lang.Number, ValueProcessingFunction> arrayOfAbstractNumberValueProperty =
            new AbstractRawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayOfAbstractNumberValue.getExpressionFunction(),
                    this.setArrayOfAbstractNumberValue.getExpressionFunction()));

    public final AbstractRawCollectionExpression<ROOT, java.lang.Number, ValueProcessingFunction> listOfAbstractNumberValueProperty =
            new AbstractRawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getListOfAbstractNumberValue.getExpressionFunction(),
                    this.setListOfAbstractNumberValue.getExpressionFunction()));

    public final AbstractRawExpression<ROOT, java.lang.CharSequence, ValueProcessingFunction> interfaceValueProperty =
            new AbstractRawExpression<>(this, new PropertyAccessorFunction(
                    this.getInterfaceValue.getExpressionFunction(),
                    this.setInterfaceValue.getExpressionFunction()));

    public final AbstractRawArrayExpression<ROOT, java.lang.CharSequence, ValueProcessingFunction> arrayOfInterfaceValueProperty =
            new AbstractRawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayOfInterfaceValue.getExpressionFunction(),
                    this.setArrayOfInterfaceValue.getExpressionFunction()));

    public final AbstractRawCollectionExpression<ROOT, java.lang.CharSequence, ValueProcessingFunction> listOfInterfaceValueProperty =
            new AbstractRawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getListOfInterfaceValue.getExpressionFunction(),
                    this.setListOfInterfaceValue.getExpressionFunction()));

    public SimpleFieldMappingDsl(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
