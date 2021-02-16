package io.mappingdsl.core.tests.fixtures.common.timezone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class TimeZone {

    public int utcOffsetHours;
    public int utcOffsetMinutes;

}

