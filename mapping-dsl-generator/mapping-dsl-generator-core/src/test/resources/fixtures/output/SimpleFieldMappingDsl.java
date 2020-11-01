package pojo;

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ExpressionFunction;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.PathProcessingFunction;
import io.mappingdsl.core.expression.function.RootIdentityFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;
import io.mappingdsl.core.expression.function.ValueProducerFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class SimpleFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHost<ROOT, pojo.SimpleField, FUN> {

    public static SimpleFieldMappingDsl<pojo.SimpleField, ValueProducerFunction> $this =
            new SimpleFieldMappingDsl<>(new RootIdentityFunction());

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> val =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "val"));

    public ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> anotherVal =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "anotherVal"));

    public SimpleFieldMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
