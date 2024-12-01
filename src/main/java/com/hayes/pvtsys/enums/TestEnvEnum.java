package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum TestEnvEnum {

    SIT(1),

    UAT(2),

    RT(4),

    PVT(8);

    private final int value;
    public static String getEnv(int category){
        for (TestEnvEnum categoryEnum: TestEnvEnum.values()){
            if ((categoryEnum.value & category) > 0){
                return categoryEnum.name();
            }
        }
        return null;
    }
}
