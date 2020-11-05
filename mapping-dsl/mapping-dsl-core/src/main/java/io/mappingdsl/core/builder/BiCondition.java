package io.mappingdsl.core.builder;

public interface BiCondition<SRC_TYPE, TRG_TYPE> {

    boolean testForward(SRC_TYPE source, TRG_TYPE target);

    boolean testBackward(TRG_TYPE target, SRC_TYPE source);

}
