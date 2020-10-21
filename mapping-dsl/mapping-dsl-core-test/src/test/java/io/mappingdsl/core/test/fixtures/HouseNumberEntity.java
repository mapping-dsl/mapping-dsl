package io.mappingdsl.core.test.fixtures;

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
