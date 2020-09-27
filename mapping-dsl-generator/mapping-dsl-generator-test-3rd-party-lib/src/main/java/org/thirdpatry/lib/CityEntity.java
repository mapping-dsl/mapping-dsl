package org.thirdpatry.lib;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CityEntity {

    private String name;

    private CountryEntity country;

    private int population;

    private Date foundation;

}
