<#-- @ftlvariable name="" type="io.mappingdsl.generator.core.model.WrapperClassModel" -->
<#import "fields.ftl" as fieldDefinitions>
package ${packageName};

import io.mappingdsl.core.expression.DslHost;
import io.mappingdsl.core.expression.ValueExpression;
import io.mappingdsl.core.expression.function.ObjectFieldAccessorFunction;
import io.mappingdsl.core.expression.function.ValueProcessingFunction;

import javax.annotation.Generated;

@Generated("MappingDsl")
public class ${dslClassName}<ROOT> extends DslHost<ROOT, ${originalClassName}> {

    public static ${dslClassName}<${originalClassName}> $this = new ${dslClassName}<>();

    <#list fieldModels as fieldModel>
        <#switch fieldModel.modelType>
            <#case "VALUE">
                <@fieldDefinitions.valueField model=fieldModel />
                <#break>
        </#switch>
    </#list>

}
