package io.mappingdsl.mavenplugin.test.lombok.fixtures;

import io.mappingdsl.core.expression.DslHostExpression;
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
public final class SimplePojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHostExpression<ROOT, io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, FUN> {

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

    public final ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getGettableValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getGettableValue"));

    public final ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setSettableValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setSettableValue"));

    public final ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getPropertyValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getPropertyValue"));

    public final ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setPropertyValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setPropertyValue"));

    public final ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getRegularValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getRegularValue"));

    public final ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setRegularValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setRegularValue"));

    public final ValueExpression<ROOT, java.lang.String, PropertyAccessorFunction> propertyValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getPropertyValue.getExpressionFunction(),
                    this.setPropertyValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.String, PropertyAccessorFunction> regularValueProperty =
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
