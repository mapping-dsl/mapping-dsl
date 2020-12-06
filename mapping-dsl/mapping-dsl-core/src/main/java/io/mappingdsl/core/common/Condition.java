package io.mappingdsl.core.common;

@FunctionalInterface
public interface Condition<SRC_TYPE> {

    boolean test(SRC_TYPE source);

}
