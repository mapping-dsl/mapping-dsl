package org.thirdpatry.lib;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CityTransport {

    private String name;

    private CountryTransport country;

    private int population;

    private Date foundation;

}
