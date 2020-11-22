package pojo;

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
public class ComplexFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslHost<ROOT, pojo.ComplexField, FUN> {

    public static ComplexFieldMappingDsl<pojo.ComplexField, ValueProducerFunction> $this =
            new ComplexFieldMappingDsl<>(new RootIdentityFunction("ComplexField"));

    public SimpleFieldMappingDsl<ROOT, PathProcessingFunction> field =
            new SimpleFieldMappingDsl<>(this, new ObjectFieldAccessorFunction(pojo.SimpleField.class, "field"));

    public SimpleFieldMappingDsl<ROOT, GetMethodAccessorFunction> getField =
            new SimpleFieldMappingDsl<>(this, new GetMethodAccessorFunction(pojo.SimpleField.class, "getField"));

    public SimpleFieldMappingDsl<ROOT, SetMethodAccessorFunction> setField =
            new SimpleFieldMappingDsl<>(this, new SetMethodAccessorFunction(pojo.SimpleField.class, "setField"));

    public SimpleFieldMappingDsl<ROOT, PropertyAccessorFunction> fieldProperty =
            new SimpleFieldMappingDsl<>(this, new PropertyAccessorFunction(
                    this.getField.getExpressionFunction(),
                    this.setField.getExpressionFunction()));

    public ComplexFieldMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private ComplexFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
