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
public final class ComplexFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.ComplexField, FUN> {

    public static final ComplexFieldMappingDsl<pojo.ComplexField, ValueProducerFunction> $this =
            new ComplexFieldMappingDsl<>(new RootIdentityFunction("ComplexField"));

    public final pojo.SimpleFieldMappingDsl<ROOT, PathProcessingFunction> field =
            new pojo.SimpleFieldMappingDsl<>(this, new ObjectFieldAccessorFunction(pojo.SimpleField.class, "field"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> collectionField =
            new DslCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, pojo.SimpleField.class, "collectionField"));

    public final DslArrayExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> arrayField =
            new DslArrayExpression<>(this, new ArrayFieldAccessorFunction(pojo.SimpleField.class, "arrayField"));

    public final pojo.AbstractValueMappingDsl<ROOT, PathProcessingFunction> abstractValue =
            new pojo.AbstractValueMappingDsl<>(this, new ObjectFieldAccessorFunction(pojo.AbstractValue.class, "abstractValue"));

    public final AbstractDslArrayExpression<ROOT, pojo.AbstractValue, ValueProcessingFunction> arrayOfAbstractValue =
            new AbstractDslArrayExpression<>(this, new ArrayFieldAccessorFunction(pojo.AbstractValue.class, "arrayOfAbstractValue"));

    public final AbstractDslCollectionExpression<ROOT, pojo.AbstractValue, ValueProcessingFunction> listOfAbstractValue =
            new AbstractDslCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, pojo.AbstractValue.class, "listOfAbstractValue"));

    public final AbstractRawExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceValue =
            new AbstractRawExpression<>(this, new ObjectFieldAccessorFunction(pojo.InterfaceValue.class, "interfaceValue"));

    public final AbstractRawArrayExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceValues =
            new AbstractRawArrayExpression<>(this, new ArrayFieldAccessorFunction(pojo.InterfaceValue.class, "interfaceValues"));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceValuesList =
            new AbstractRawCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, pojo.InterfaceValue.class, "interfaceValuesList"));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceSuccessorsList =
            new AbstractRawCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, pojo.InterfaceValue.class, "interfaceSuccessorsList"));

    public final RawCollectionExpression<ROOT, java.lang.Object, ValueProcessingFunction> interfacePredecessorsList =
            new RawCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, java.lang.Object.class, "interfacePredecessorsList"));

    public final pojo.SimpleFieldMappingDsl<ROOT, ValueProducerFunction> getField =
            new pojo.SimpleFieldMappingDsl<>(this, new GetMethodAccessorFunction(pojo.SimpleField.class, "getField"));

    public final DslExpression<ROOT, pojo.SimpleField, ValueConsumerFunction> setField =
            new pojo.SimpleFieldMappingDsl<>(this, new SetMethodAccessorFunction(pojo.SimpleField.class, "setField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProducerFunction> getCollectionField =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getCollectionField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueConsumerFunction> setCollectionField =
            new DslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, pojo.SimpleField.class, "setCollectionField"));

    public final DslArrayExpression<ROOT, pojo.SimpleField, ValueProducerFunction> getArrayField =
            new DslArrayExpression<>(this, new GetMethodAccessorFunction(pojo.SimpleField[].class, "getArrayField"));

    public final DslArrayExpression<ROOT, pojo.SimpleField, ValueConsumerFunction> setArrayField =
            new DslArrayExpression<>(this, new SetArrayMethodAccessorFunction(pojo.SimpleField[].class, pojo.SimpleField.class, "setArrayField"));

    public final pojo.AbstractValueMappingDsl<ROOT, ValueProducerFunction> getAbstractValue =
            new pojo.AbstractValueMappingDsl<>(this, new GetMethodAccessorFunction(pojo.AbstractValue.class, "getAbstractValue"));

    public final AbstractDslExpression<ROOT, pojo.AbstractValue, ValueConsumerFunction> setAbstractValue =
            new pojo.AbstractValueMappingDsl<>(this, new SetMethodAccessorFunction(pojo.AbstractValue.class, "setAbstractValue"));

    public final AbstractDslArrayExpression<ROOT, pojo.AbstractValue, ValueProducerFunction> getArrayOfAbstractValue =
            new AbstractDslArrayExpression<>(this, new GetMethodAccessorFunction(pojo.AbstractValue[].class, "getArrayOfAbstractValue"));

    public final AbstractDslArrayExpression<ROOT, pojo.AbstractValue, ValueConsumerFunction> setArrayOfAbstractValue =
            new AbstractDslArrayExpression<>(this, new SetArrayMethodAccessorFunction(pojo.AbstractValue[].class, pojo.AbstractValue.class, "setArrayOfAbstractValue"));

    public final AbstractDslCollectionExpression<ROOT, pojo.AbstractValue, ValueProducerFunction> getListOfAbstractValue =
            new AbstractDslCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getListOfAbstractValue"));

    public final AbstractDslCollectionExpression<ROOT, pojo.AbstractValue, ValueConsumerFunction> setListOfAbstractValue =
            new AbstractDslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, pojo.AbstractValue.class, "setListOfAbstractValue"));

    public final AbstractRawExpression<ROOT, pojo.InterfaceValue, ValueProducerFunction> getInterfaceValue =
            new AbstractRawExpression<>(this, new GetMethodAccessorFunction(pojo.InterfaceValue.class, "getInterfaceValue"));

    public final AbstractRawExpression<ROOT, pojo.InterfaceValue, ValueConsumerFunction> setInterfaceValue =
            new AbstractRawExpression<>(this, new SetMethodAccessorFunction(pojo.InterfaceValue.class, "setInterfaceValue"));

    public final AbstractRawArrayExpression<ROOT, pojo.InterfaceValue, ValueProducerFunction> getInterfaceValues =
            new AbstractRawArrayExpression<>(this, new GetMethodAccessorFunction(pojo.InterfaceValue[].class, "getInterfaceValues"));

    public final AbstractRawArrayExpression<ROOT, pojo.InterfaceValue, ValueConsumerFunction> setInterfaceValues =
            new AbstractRawArrayExpression<>(this, new SetArrayMethodAccessorFunction(pojo.InterfaceValue[].class, pojo.InterfaceValue.class, "setInterfaceValues"));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueProducerFunction> getInterfaceValuesList =
            new AbstractRawCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getInterfaceValuesList"));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueConsumerFunction> setInterfaceValuesList =
            new AbstractRawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, pojo.InterfaceValue.class, "setInterfaceValuesList"));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueProducerFunction> getInterfaceSuccessorsList =
            new AbstractRawCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getInterfaceSuccessorsList"));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueConsumerFunction> setInterfaceSuccessorsList =
            new AbstractRawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, pojo.InterfaceValue.class, "setInterfaceSuccessorsList"));

    public final RawCollectionExpression<ROOT, java.lang.Object, ValueProducerFunction> getInterfacePredecessorsList =
            new RawCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getInterfacePredecessorsList"));

    public final RawCollectionExpression<ROOT, java.lang.Object, ValueConsumerFunction> setInterfacePredecessorsList =
            new RawCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, java.lang.Object.class, "setInterfacePredecessorsList"));

    public final pojo.SimpleFieldMappingDsl<ROOT, PathProcessingFunction> fieldProperty =
            new pojo.SimpleFieldMappingDsl<>(this, new PropertyAccessorFunction(
                    this.getField.getExpressionFunction(),
                    this.setField.getExpressionFunction()));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> collectionFieldProperty =
            new DslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getCollectionField.getExpressionFunction(),
                    this.setCollectionField.getExpressionFunction()));

    public final DslArrayExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> arrayFieldProperty =
            new DslArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayField.getExpressionFunction(),
                    this.setArrayField.getExpressionFunction()));

    public final pojo.AbstractValueMappingDsl<ROOT, PathProcessingFunction> abstractValueProperty =
            new pojo.AbstractValueMappingDsl<>(this, new PropertyAccessorFunction(
                    this.getAbstractValue.getExpressionFunction(),
                    this.setAbstractValue.getExpressionFunction()));

    public final AbstractDslArrayExpression<ROOT, pojo.AbstractValue, ValueProcessingFunction> arrayOfAbstractValueProperty =
            new AbstractDslArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayOfAbstractValue.getExpressionFunction(),
                    this.setArrayOfAbstractValue.getExpressionFunction()));

    public final AbstractDslCollectionExpression<ROOT, pojo.AbstractValue, ValueProcessingFunction> listOfAbstractValueProperty =
            new AbstractDslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getListOfAbstractValue.getExpressionFunction(),
                    this.setListOfAbstractValue.getExpressionFunction()));

    public final AbstractRawExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceValueProperty =
            new AbstractRawExpression<>(this, new PropertyAccessorFunction(
                    this.getInterfaceValue.getExpressionFunction(),
                    this.setInterfaceValue.getExpressionFunction()));

    public final AbstractRawArrayExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceValuesProperty =
            new AbstractRawArrayExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getInterfaceValues.getExpressionFunction(),
                    this.setInterfaceValues.getExpressionFunction()));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceValuesListProperty =
            new AbstractRawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getInterfaceValuesList.getExpressionFunction(),
                    this.setInterfaceValuesList.getExpressionFunction()));

    public final AbstractRawCollectionExpression<ROOT, pojo.InterfaceValue, ValueProcessingFunction> interfaceSuccessorsListProperty =
            new AbstractRawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getInterfaceSuccessorsList.getExpressionFunction(),
                    this.setInterfaceSuccessorsList.getExpressionFunction()));

    public final RawCollectionExpression<ROOT, java.lang.Object, ValueProcessingFunction> interfacePredecessorsListProperty =
            new RawCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getInterfacePredecessorsList.getExpressionFunction(),
                    this.setInterfacePredecessorsList.getExpressionFunction()));

    public ComplexFieldMappingDsl(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private ComplexFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
