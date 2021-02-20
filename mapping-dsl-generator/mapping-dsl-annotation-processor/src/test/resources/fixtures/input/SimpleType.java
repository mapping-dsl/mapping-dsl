package pojo;

import io.mappingdsl.annotation.MappingDsl;

import java.util.List;

@MappingDsl
public class SimpleType {

    private String stringValue;

    private int intValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

}
