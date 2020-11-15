package io.mappingdsl.core.tests.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseNumberEntity {

    private Integer number;

    private String suffix;

    private Geolocation geolocation;

    public HouseNumberEntity(Integer number, String suffix) {
        this.number = number;
        this.suffix = suffix;
    }

}
