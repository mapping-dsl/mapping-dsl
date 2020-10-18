package pojo;

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class SimpleFieldMappingDsl<ROOT> extends DslHost<ROOT, pojo.SimpleField> {

    public static SimpleFieldMappingDsl<pojo.SimpleField> $this = new SimpleFieldMappingDsl<>();

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> val =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "val"));

}
