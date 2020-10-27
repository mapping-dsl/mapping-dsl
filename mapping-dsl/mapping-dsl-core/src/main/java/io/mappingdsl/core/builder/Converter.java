package io.mappingdsl.core.builder;

@FunctionalInterface
public interface Converter<SRC_TYPE, TRG_TYPE> {

    TRG_TYPE convert(SRC_TYPE source);

}
