package io.mappingdsl.generator.core.model;

public enum FieldModelType {

    /**
     * Represents regular field of type that is not defined in the generator scope.
     */
    VALUE,

    /**
     * Represents field of type that is explicitly defined in the generator scope.
     * For this type of fields DSL will be generated using respective DSL wrapper class.
     */
    DSL_WRAPPER

}
