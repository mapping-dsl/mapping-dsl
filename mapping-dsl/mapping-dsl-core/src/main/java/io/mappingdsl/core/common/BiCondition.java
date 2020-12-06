package io.mappingdsl.core.common;

public interface BiCondition<SRC_TYPE, TRG_TYPE> {

    boolean testForward(SRC_TYPE source);

    boolean testBackward(TRG_TYPE target);

}
