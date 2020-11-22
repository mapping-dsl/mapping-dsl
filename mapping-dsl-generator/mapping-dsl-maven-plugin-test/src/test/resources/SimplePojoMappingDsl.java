package io.mappingdsl.mavenplugin.test.lombok.fixtures;

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
public class SimplePojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHost<ROOT, io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, FUN> {

    public static SimplePojoMappingDsl<io.mappingdsl.mavenplugin.test.lombok.fixtures.SimplePojo, ValueProducerFunction> $this =
            new SimplePojoMappingDsl<>(new RootIdentityFunction("SimplePojo"));

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> gettableValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "gettableValue"));

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> settableValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "settableValue"));

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> propertyValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "propertyValue"));

    public ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getGettableValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getGettableValue"));

    public ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setSettableValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setSettableValue"));

    public ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getPropertyValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getPropertyValue"));

    public ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setPropertyValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setPropertyValue"));

    public ValueExpression<ROOT, java.lang.String, PropertyAccessorFunction> propertyValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getPropertyValue.getExpressionFunction(),
                    this.setPropertyValue.getExpressionFunction()));

    public SimplePojoMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimplePojoMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
