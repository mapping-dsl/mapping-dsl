package io.mappingdsl.core.builder;

public interface BiConverter<SRC_TYPE, TRG_TYPE> {

    TRG_TYPE convertForward(SRC_TYPE source);

    SRC_TYPE convertBackward(TRG_TYPE target);

}
