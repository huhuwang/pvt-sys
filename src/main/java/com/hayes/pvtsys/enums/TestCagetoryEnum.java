package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestCagetoryEnum {

    SIT(1),

    UAT(2),

    RT(4),

    PVT(8);

    private final int value;
    public static String getEvn(int category){
        for (TestCagetoryEnum categoryEnum: TestCagetoryEnum.values()){
            if ((categoryEnum.value & category) > 0){
                return categoryEnum.name();
            }
        }
        return null;
    }
}
