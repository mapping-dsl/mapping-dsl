package io.mappingdsl.core.tests.fixtures;

import io.mappingdsl.core.tests.fixtures.common.NamedObject;
import io.mappingdsl.core.tests.fixtures.common.timezone.TimeZone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public abstract class SettlementDto extends NamedObject {

    @Getter
    @Setter
    private TimeZone timeZone;

    public SettlementDto(String name) {
        super(name);
    }

}
