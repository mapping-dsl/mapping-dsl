package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRules;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiAndTypeBuilder<SRC_ROOT> {

    private final Class<SRC_ROOT> sourceType;

    public <TRG_ROOT> BiInitialExpressionBuilder<SRC_ROOT, TRG_ROOT> and(Class<TRG_ROOT> rightSideType) {
        MappingKey<SRC_ROOT, TRG_ROOT> mappingKey = new MappingKey<>(this.sourceType, rightSideType);
        return new BiInitialExpressionBuilder<>(mappingKey, new MappingRules());
    }

}
