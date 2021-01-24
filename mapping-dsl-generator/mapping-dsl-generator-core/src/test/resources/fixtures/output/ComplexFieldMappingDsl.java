package pojo;

import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueCollectionExpression;
import io.mappingdsl.core.expression.ValueExpression;
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

import javax.annotation.Generated;

@Generated("MappingDsl")
public final class ComplexFieldMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.ComplexField, FUN> {

    public static final ComplexFieldMappingDsl<pojo.ComplexField, ValueProducerFunction> $this =
            new ComplexFieldMappingDsl<>(new RootIdentityFunction("ComplexField"));

    public final SimpleFieldMappingDsl<ROOT, PathProcessingFunction> field =
            new SimpleFieldMappingDsl<>(this, new ObjectFieldAccessorFunction(pojo.SimpleField.class, "field"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> collectionField =
            new DslCollectionExpression<>(this, new CollectionFieldAccessorFunction(java.util.List.class, pojo.SimpleField.class, "collectionField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> arrayField =
            new DslCollectionExpression<>(this, new ArrayFieldAccessorFunction(pojo.SimpleField.class, "arrayField"));

    public final DslExpression<ROOT, pojo.SimpleField, ValueProducerFunction> getField =
            new SimpleFieldMappingDsl<>(this, new GetMethodAccessorFunction(pojo.SimpleField.class, "getField"));

    public final DslExpression<ROOT, pojo.SimpleField, ValueConsumerFunction> setField =
            new SimpleFieldMappingDsl<>(this, new SetMethodAccessorFunction(pojo.SimpleField.class, "setField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProducerFunction> getCollectionField =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(java.util.List.class, "getCollectionField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueConsumerFunction> setCollectionField =
            new DslCollectionExpression<>(this, new SetCollectionMethodAccessorFunction(java.util.List.class, pojo.SimpleField.class, "setCollectionField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProducerFunction> getArrayField =
            new DslCollectionExpression<>(this, new GetMethodAccessorFunction(pojo.SimpleField[].class, "getArrayField"));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueConsumerFunction> setArrayField =
            new DslCollectionExpression<>(this, new SetArrayMethodAccessorFunction(pojo.SimpleField[].class, pojo.SimpleField.class, "setArrayField"));

    public final SimpleFieldMappingDsl<ROOT, PathProcessingFunction> fieldProperty =
            new SimpleFieldMappingDsl<>(this, new PropertyAccessorFunction(
                    this.getField.getExpressionFunction(),
                    this.setField.getExpressionFunction()));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> collectionFieldProperty =
            new DslCollectionExpression<>(this, new CollectionPropertyAccessorFunction(
                    this.getCollectionField.getExpressionFunction(),
                    this.setCollectionField.getExpressionFunction()));

    public final DslCollectionExpression<ROOT, pojo.SimpleField, ValueProcessingFunction> arrayFieldProperty =
            new DslCollectionExpression<>(this, new ArrayPropertyAccessorFunction(
                    this.getArrayField.getExpressionFunction(),
                    this.setArrayField.getExpressionFunction()));

    public ComplexFieldMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private ComplexFieldMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
