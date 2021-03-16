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

    public AnotherTypeMappingDsl(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private AnotherTypeMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
