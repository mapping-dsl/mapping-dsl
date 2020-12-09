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
public final class SimpleDataPojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHostExpression<ROOT, io.mappingdsl.mavenplugin.test.lombok.fixtures.SimpleDataPojo, FUN> {

    public static final SimpleDataPojoMappingDsl<io.mappingdsl.mavenplugin.test.lombok.fixtures.SimpleDataPojo, ValueProducerFunction> $this =
            new SimpleDataPojoMappingDsl<>(new RootIdentityFunction("SimpleDataPojo"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> simpleValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "simpleValue"));

    public final ValueExpression<ROOT, java.lang.String, GetMethodAccessorFunction> getSimpleValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getSimpleValue"));

    public final ValueExpression<ROOT, java.lang.String, SetMethodAccessorFunction> setSimpleValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setSimpleValue"));

    public final ValueExpression<ROOT, java.lang.String, PropertyAccessorFunction> simpleValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getSimpleValue.getExpressionFunction(),
                    this.setSimpleValue.getExpressionFunction()));

    public SimpleDataPojoMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleDataPojoMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
