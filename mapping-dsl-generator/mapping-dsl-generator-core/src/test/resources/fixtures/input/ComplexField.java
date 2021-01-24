package pojo;

import java.util.List;

public class ComplexField {

    private SimpleField field;

    private List<SimpleField> collectionField;

    private SimpleField[] arrayField;

    public SimpleField getField() {
        return field;
    }

    public void setField(SimpleField field) {
        this.field = field;
    }

    public List<SimpleField> getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(List<SimpleField> collectionField) {
        this.collectionField = collectionField;
    }

    public SimpleField[] getArrayField() {
        return arrayField;
    }

    public void setArrayField(SimpleField[] arrayField) {
        this.arrayField = arrayField;
    }

}
