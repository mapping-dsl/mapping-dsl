package org.thirdpatry.lib;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class City {

    private String name;

    private Country country;

    private int population;

    private Date foundation;

}
