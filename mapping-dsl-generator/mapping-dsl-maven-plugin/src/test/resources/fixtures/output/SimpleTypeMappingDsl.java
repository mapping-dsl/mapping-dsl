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
public final class SimpleTypeMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.SimpleType, FUN> {

    public static final SimpleTypeMappingDsl<pojo.SimpleType, ValueProducerFunction> $this =
            new SimpleTypeMappingDsl<>(new RootIdentityFunction("SimpleType"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "stringValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(int.class, "intValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProducerFunction> getStringValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getStringValue"));

    public final RawExpression<ROOT, java.lang.String, ValueConsumerFunction> setStringValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setStringValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueProducerFunction> getIntValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(int.class, "getIntValue"));

    public final RawExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setIntValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(int.class, "setIntValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getStringValue.getExpressionFunction(),
                    this.setStringValue.getExpressionFunction()));

    public final RawExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getIntValue.getExpressionFunction(),
                    this.setIntValue.getExpressionFunction()));

    public SimpleTypeMappingDsl(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleTypeMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
