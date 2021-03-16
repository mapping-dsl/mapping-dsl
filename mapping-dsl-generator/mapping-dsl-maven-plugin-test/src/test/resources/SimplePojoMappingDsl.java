package io.mappingdsl.mavenplugin.test.lombok.fixtures;

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
public final class SimplePojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, FUN> {

    public static final SimplePojoMappingDsl<io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, ValueProducerFunction> $this =
            new SimplePojoMappingDsl<>(new RootIdentityFunction("SimplePojo"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> gettableValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "gettableValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> settableValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "settableValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> propertyValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "propertyValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> regularValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "regularValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProducerFunction> getGettableValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getGettableValue"));

    public final RawExpression<ROOT, java.lang.String, ValueConsumerFunction> setSettableValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setSettableValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProducerFunction> getPropertyValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getPropertyValue"));

    public final RawExpression<ROOT, java.lang.String, ValueConsumerFunction> setPropertyValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setPropertyValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProducerFunction> getRegularValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getRegularValue"));

    public final RawExpression<ROOT, java.lang.String, ValueConsumerFunction> setRegularValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setRegularValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> propertyValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getPropertyValue.getExpressionFunction(),
                    this.setPropertyValue.getExpressionFunction()));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> regularValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getRegularValue.getExpressionFunction(),
                    this.setRegularValue.getExpressionFunction()));

    public SimplePojoMappingDsl(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimplePojoMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
