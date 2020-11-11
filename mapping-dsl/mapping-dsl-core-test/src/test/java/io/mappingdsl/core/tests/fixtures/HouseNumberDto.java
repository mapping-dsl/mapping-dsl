package io.mappingdsl.core.tests.fixtures;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseNumberDto {

    private Integer number;

    private String suffix;

    private Geolocation geolocation;

}
