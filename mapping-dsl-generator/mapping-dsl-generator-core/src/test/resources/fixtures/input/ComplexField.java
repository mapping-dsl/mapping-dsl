package pojo;

import java.util.List;

public class ComplexField {

    private SimpleField field;

    private List<SimpleField> collectionField;

    private SimpleField[] arrayField;

    private AbstractValue abstractValue;

    private AbstractValue[] arrayOfAbstractValue;

    private List<AbstractValue> listOfAbstractValue;

    private InterfaceValue interfaceValue;

    private InterfaceValue[] interfaceValues;

    private List<InterfaceValue> interfaceValuesList;

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

    public AbstractValue getAbstractValue() {
        return abstractValue;
    }

    public void setAbstractValue(AbstractValue abstractValue) {
        this.abstractValue = abstractValue;
    }

    public AbstractValue[] getArrayOfAbstractValue() {
        return arrayOfAbstractValue;
    }

    public void setArrayOfAbstractValue(AbstractValue[] arrayOfAbstractValue) {
        this.arrayOfAbstractValue = arrayOfAbstractValue;
    }

    public List<AbstractValue> getListOfAbstractValue() {
        return listOfAbstractValue;
    }

    public void setListOfAbstractValue(List<AbstractValue> listOfAbstractValue) {
        this.listOfAbstractValue = listOfAbstractValue;
    }

    public InterfaceValue getInterfaceValue() {
        return interfaceValue;
    }

    public void setInterfaceValue(InterfaceValue interfaceValue) {
        this.interfaceValue = interfaceValue;
    }

    public InterfaceValue[] getInterfaceValues() {
        return interfaceValues;
    }

    public void setInterfaceValues(InterfaceValue[] interfaceValues) {
        this.interfaceValues = interfaceValues;
    }

    public List<InterfaceValue> getInterfaceValuesList() {
        return interfaceValuesList;
    }

    public void setInterfaceValuesList(List<InterfaceValue> interfaceValuesList) {
        this.interfaceValuesList = interfaceValuesList;
    }

}
