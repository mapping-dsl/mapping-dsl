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
public final class AnotherTypeMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, pojo.AnotherType, FUN> {

    public static final AnotherTypeMappingDsl<pojo.AnotherType, ValueProducerFunction> $this =
            new AnotherTypeMappingDsl<>(new RootIdentityFunction("AnotherType"));

    public final pojo.SimpleTypeMappingDsl<ROOT, PathProcessingFunction> simpleValue =
            new pojo.SimpleTypeMappingDsl<>(this, new ObjectFieldAccessorFunction(pojo.SimpleType.class, "simpleValue"));

    public final pojo.SimpleTypeMappingDsl<ROOT, ValueProducerFunction> getSimpleValue =
            new pojo.SimpleTypeMappingDsl<>(this, new GetMethodAccessorFunction(pojo.SimpleType.class, "getSimpleValue"));

    public final DslExpression<ROOT, pojo.SimpleType, ValueConsumerFunction> setSimpleValue =
            new pojo.SimpleTypeMappingDsl<>(this, new SetMethodAccessorFunction(pojo.SimpleType.class, "setSimpleValue"));

    public final pojo.SimpleTypeMappingDsl<ROOT, PathProcessingFunction> simpleValueProperty =
            new pojo.SimpleTypeMappingDsl<>(this, new PropertyAccessorFunction(
                    this.getSimpleValue.getExpressionFunction(),
                    this.setSimpleValue.getExpressionFunction()));

    public AnotherTypeMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private AnotherTypeMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
