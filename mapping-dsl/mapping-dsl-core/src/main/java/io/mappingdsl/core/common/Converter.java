package io.mappingdsl.core.common;

@FunctionalInterface
public interface Converter<SRC_TYPE, TRG_TYPE> {

    TRG_TYPE convert(SRC_TYPE source);

}
