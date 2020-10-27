package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRules;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniTerminalTypeBuilder<SRC_ROOT> {

    private final Class<SRC_ROOT> sourceType;

    public <TRG_ROOT> UniInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> to(Class<TRG_ROOT> targetType) {
        MappingKey<SRC_ROOT, TRG_ROOT> mappingKey = new MappingKey<>(this.sourceType, targetType);
        return new UniInitialExpressionBuilder<>(mappingKey, new MappingRules());
    }

}
