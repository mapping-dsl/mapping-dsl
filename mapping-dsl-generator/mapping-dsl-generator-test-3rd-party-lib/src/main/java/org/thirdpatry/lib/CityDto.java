package org.thirdpatry.lib;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CityDto {

    private String name;

    private CountryDto country;

    private int population;

    private Date foundation;

}
