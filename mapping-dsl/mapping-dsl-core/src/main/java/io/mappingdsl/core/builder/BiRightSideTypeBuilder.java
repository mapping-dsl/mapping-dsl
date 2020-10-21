package io.mappingdsl.core.builder;

import io.mappingdsl.core.MappingKey;
import io.mappingdsl.core.MappingRules;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BiRightSideTypeBuilder<SRC_ROOT> {

    private final Class<SRC_ROOT> sourceType;

    public <TRG_ROOT> BiLeftSideExpressionBuilder<SRC_ROOT, TRG_ROOT> and(Class<TRG_ROOT> rightSideType) {
        MappingKey<SRC_ROOT, TRG_ROOT> mappingKey = new MappingKey<>(this.sourceType, rightSideType);
        return new BiLeftSideExpressionBuilder<>(mappingKey, new MappingRules());
    }

}
