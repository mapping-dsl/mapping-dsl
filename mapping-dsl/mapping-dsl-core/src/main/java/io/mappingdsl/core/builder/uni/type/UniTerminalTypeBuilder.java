package io.mappingdsl.core.builder.uni.type;

import io.mappingdsl.core.MappingContext;
import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.builder.uni.expression.UniInitialExpressionBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UniTerminalTypeBuilder<SRC_ROOT> {

    private final MappingContext<?, ?> context;
    private final Class<SRC_ROOT> sourceType;

    public <TRG_ROOT> UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> to(Class<TRG_ROOT> terminalType) {
        MappingKey<SRC_ROOT, TRG_ROOT> mappingKey = new MappingKey<>(this.sourceType, terminalType);
        return new UniInitialExpressionBuilder<>(this.context.withMappingKey(mappingKey));
    }

}
