package pojo;

import io.mappingdsl.annotation.MappingDsl;

import java.util.List;

@MappingDsl
public class AnotherType {

    private SimpleType simpleValue;

    public SimpleType getSimpleValue() {
        return simpleValue;
    }

    public void setSimpleValue(SimpleType simpleValue) {
        this.simpleValue = simpleValue;
    }

}
