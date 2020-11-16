package io.mappingdsl.core.builder.uni.type;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.builder.uni.expression.UniExpressionInitiatorBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTypeTerminatorBuilder<SRC_ROOT> {

    private final MappingContext<?, ?> context;
    private final Class<SRC_ROOT> sourceType;

    public <TRG_ROOT> UniExpressionInitiatorBuilder<SRC_ROOT, TRG_ROOT> to(Class<TRG_ROOT> targetType) {
        MappingKey<SRC_ROOT, TRG_ROOT> mappingKey = new MappingKey<>(this.sourceType, targetType);
        return new UniExpressionInitiatorBuilder<>(this.context.withMappingKey(mappingKey));
    }

}
