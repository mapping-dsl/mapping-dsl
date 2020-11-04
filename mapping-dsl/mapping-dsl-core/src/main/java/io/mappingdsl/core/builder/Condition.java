package io.mappingdsl.core.builder;

@FunctionalInterface
public interface Condition<SRC_TYPE, TRG_TYPE> {

    boolean test(SRC_TYPE source, TRG_TYPE target);

}
