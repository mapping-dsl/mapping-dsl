package io.mappingdsl.mavenplugin.test.lombok.fixtures;

import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.array.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.array.AbstractValueArrayExpression;
import io.mappingdsl.core.expression.array.DslArrayExpression;
import io.mappingdsl.core.expression.array.ValueArrayExpression;
import io.mappingdsl.core.expression.collection.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.collection.AbstractValueCollectionExpression;
import io.mappingdsl.core.expression.collection.DslCollectionExpression;
import io.mappingdsl.core.expression.collection.ValueCollectionExpression;
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
import io.mappingdsl.core.expression.simple.AbstractValueExpression;
import io.mappingdsl.core.expression.simple.DslExpression;
import io.mappingdsl.core.expression.simple.ValueExpression;

import javax.annotation.Generated;

@Generated("MappingDsl")
public final class SimplePojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, FUN> {

    public static final SimplePojoMappingDsl<io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, ValueProducerFunction> $this =
            new SimplePojoMappingDsl<>(new RootIdentityFunction("SimplePojo"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> gettableValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "gettableValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> settableValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "settableValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> propertyValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "propertyValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> regularValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "regularValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProducerFunction> getGettableValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getGettableValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueConsumerFunction> setSettableValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setSettableValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProducerFunction> getPropertyValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getPropertyValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueConsumerFunction> setPropertyValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setPropertyValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProducerFunction> getRegularValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getRegularValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueConsumerFunction> setRegularValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setRegularValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> propertyValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getPropertyValue.getExpressionFunction(),
                    this.setPropertyValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> regularValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getRegularValue.getExpressionFunction(),
                    this.setRegularValue.getExpressionFunction()));

    public SimplePojoMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimplePojoMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
