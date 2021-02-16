package io.mappingdsl.core.tests.fixtures.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Geolocation {

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;

}
