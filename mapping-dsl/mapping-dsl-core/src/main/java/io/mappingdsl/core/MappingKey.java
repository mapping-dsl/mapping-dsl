package io.mappingdsl.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class MappingKey<SRC, TRG> {

    @Getter
    private final Class<SRC> source;

    @Getter
    private final Class<TRG> target;

    public MappingKey<TRG, SRC> invert() {
        return new MappingKey<>(this.target, this.source);
    }

}
