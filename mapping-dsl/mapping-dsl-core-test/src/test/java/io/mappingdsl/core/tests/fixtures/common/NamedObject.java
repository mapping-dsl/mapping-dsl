package io.mappingdsl.core.tests.fixtures.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public abstract class NamedObject {

    @Getter
    @Setter
    private String name;

}
