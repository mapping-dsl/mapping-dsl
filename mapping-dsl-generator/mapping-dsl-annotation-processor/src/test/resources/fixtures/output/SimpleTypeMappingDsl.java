package pojo;

import io.mappingdsl.core.expression.AbstractDslArrayExpression;
import io.mappingdsl.core.expression.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.AbstractDslExpression;
import io.mappingdsl.core.expression.AbstractValueArrayExpression;
import io.mappingdsl.core.expression.AbstractValueCollectionExpression;
import io.mappingdsl.core.expression.AbstractValueExpression;
import io.mappingdsl.core.expression.DslArrayExpression;
import io.mappingdsl.core.expression.DslCollectionExpression;
import io.mappingdsl.core.expression.DslExpression;
import io.mappingdsl.core.expression.ExpressionBase;
import io.mappingdsl.core.expression.ValueArrayExpression;
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
public final class SimpleTypeMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.SimpleType, FUN> {

    public static final SimpleTypeMappingDsl<pojo.SimpleType, ValueProducerFunction> $this =
            new SimpleTypeMappingDsl<>(new RootIdentityFunction("SimpleType"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "stringValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValue =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.Integer.class, "intValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProducerFunction> getStringValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getStringValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueConsumerFunction> setStringValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setStringValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProducerFunction> getIntValue =
            new ValueExpression<>(this, new GetMethodAccessorFunction(java.lang.Integer.class, "getIntValue"));

    public final ValueExpression<ROOT, java.lang.Integer, ValueConsumerFunction> setIntValue =
            new ValueExpression<>(this, new SetMethodAccessorFunction(java.lang.Integer.class, "setIntValue"));

    public final ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> stringValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getStringValue.getExpressionFunction(),
                    this.setStringValue.getExpressionFunction()));

    public final ValueExpression<ROOT, java.lang.Integer, ValueProcessingFunction> intValueProperty =
            new ValueExpression<>(this, new PropertyAccessorFunction(
                    this.getIntValue.getExpressionFunction(),
                    this.setIntValue.getExpressionFunction()));

    public SimpleTypeMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleTypeMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
