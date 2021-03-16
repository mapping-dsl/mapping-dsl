package io.mappingdsl.mavenplugin.test.lombok.fixtures;

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
public final class SimpleDataPojoMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends DslExpression<ROOT, io.mappingdsl.mavenplugin.test.lombok.fixtures.SimpleDataPojo, FUN> {

    public static final SimpleDataPojoMappingDsl<io.mappingdsl.mavenplugin.test.lombok.fixtures.SimpleDataPojo, ValueProducerFunction> $this =
            new SimpleDataPojoMappingDsl<>(new RootIdentityFunction("SimpleDataPojo"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> simpleValue =
            new RawExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "simpleValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProducerFunction> getSimpleValue =
            new RawExpression<>(this, new GetMethodAccessorFunction(java.lang.String.class, "getSimpleValue"));

    public final RawExpression<ROOT, java.lang.String, ValueConsumerFunction> setSimpleValue =
            new RawExpression<>(this, new SetMethodAccessorFunction(java.lang.String.class, "setSimpleValue"));

    public final RawExpression<ROOT, java.lang.String, ValueProcessingFunction> simpleValueProperty =
            new RawExpression<>(this, new PropertyAccessorFunction(
                    this.getSimpleValue.getExpressionFunction(),
                    this.setSimpleValue.getExpressionFunction()));

    public SimpleDataPojoMappingDsl(ExpressionBase<ROOT, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private SimpleDataPojoMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
