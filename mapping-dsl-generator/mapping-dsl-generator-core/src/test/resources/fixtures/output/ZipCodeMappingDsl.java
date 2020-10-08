package pojo;

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class ZipCodeMappingDsl<ROOT> extends DslHost<ROOT, pojo.ZipCode> {

    public static ZipCodeMappingDsl<pojo.ZipCode> $this = new ZipCodeMappingDsl<>();

    public ValueExpression<ROOT, java.lang.String, ValueProcessingFunction> code =
            new ValueExpression<>(this, new ObjectFieldAccessorFunction(java.lang.String.class, "code"));

}
