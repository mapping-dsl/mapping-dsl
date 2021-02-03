package pojo;

import io.mappingdsl.core.expression.AbstractDslCollectionExpression;
import io.mappingdsl.core.expression.AbstractDslExpression;
import io.mappingdsl.core.expression.AbstractValueCollectionExpression;
import io.mappingdsl.core.expression.AbstractValueExpression;
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
public final class AbstractValueMappingDsl<ROOT, FUN extends ExpressionFunction>
        extends AbstractDslExpression<ROOT, pojo.AbstractValue, FUN> {

    public static final AbstractValueMappingDsl<pojo.AbstractValue, ValueProducerFunction> $this =
            new AbstractValueMappingDsl<>(new RootIdentityFunction("AbstractValue"));

    public AbstractValueMappingDsl(ExpressionBase<ROOT, ?, ?> parentExpression, FUN expressionFunction) {
        super(parentExpression, expressionFunction);
    }

    private AbstractValueMappingDsl(FUN expressionFunction) {
        super(expressionFunction);
    }

}
