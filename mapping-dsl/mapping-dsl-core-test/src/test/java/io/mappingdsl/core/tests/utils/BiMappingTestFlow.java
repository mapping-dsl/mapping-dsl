package io.mappingdsl.core.tests.utils;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BiMappingTestFlow {

    private final boolean forwardMapped;
    private final boolean backwardMapped;

}

