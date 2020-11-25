package io.mappingdsl.core.tests.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class HouseNumberEntity {

    @Getter
    @Setter
    private Integer number;

    @Getter
    @Setter
    private String suffix;

    @Getter
    @Setter
    private Geolocation geolocation;

    public HouseNumberEntity(Integer number, String suffix) {
        this.number = number;
        this.suffix = suffix;
    }

}
