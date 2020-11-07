package io.mappingdsl.core.tests.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class HouseNumberEntity {

    @Getter
    private Integer number;

    @Getter
    private String suffix;

}
