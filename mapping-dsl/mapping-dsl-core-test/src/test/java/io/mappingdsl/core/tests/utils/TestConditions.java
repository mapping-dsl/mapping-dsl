package io.mappingdsl.core.tests.utils;

import io.mappingdsl.core.common.BiCondition;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConditions {

    public static BiCondition<Integer, Integer> greaterThanIntCondition(int value) {
        return  new BiCondition<Integer, Integer>() {

            @Override
            public boolean testForward(Integer source) {
                return source > value;
            }

            @Override
            public boolean testBackward(Integer target) {
                return target > value;
            }
        };
    };

    public static BiCondition<HouseNumberEntity, HouseNumberDto> greaterThanHouseNumberCondition(int number) {
        return new BiCondition<HouseNumberEntity, HouseNumberDto>() {

            @Override
            public boolean testForward(HouseNumberEntity source) {
                return source.getNumber() > number;
            }

            @Override
            public boolean testBackward(HouseNumberDto target) {
                return target.getNumber() > number;
            }
        };
    }

}
