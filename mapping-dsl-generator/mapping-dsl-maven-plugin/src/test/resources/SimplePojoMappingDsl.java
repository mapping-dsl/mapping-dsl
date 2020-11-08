package io.mappingdsl.mavenplugin.tests.fixtures;

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class SimplePojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHost<ROOT, io.mappingdsl.mavenplugin.tests.fixtures.SimplePojo, FUN> {

    public static SimplePojoMappingDsl<io.mappingdsl.mavenplugin.tests.fixtures.SimplePojo, ValueProducerFunction> $this =
            new SimplePojoMappingDsl<>(new RootIdentityFunction("SimplePojo"));

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "stringValue"));        

    public SimplePojoMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimplePojoMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
