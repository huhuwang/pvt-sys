package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum TestCagetoryEnum {

    SIT(1),

    UAT(2),

    RT(4),

    PVT(8);

    private final int value;
    public static String getEnv(int category){
        for (TestCagetoryEnum categoryEnum: TestCagetoryEnum.values()){
            if ((categoryEnum.value & category) > 0){
                return categoryEnum.name();
            }
        }
        return null;
    }

    public static List<String> getAllEnv(int category){
        List<String> evnList = new ArrayList<>();
        for (TestCagetoryEnum categoryEnum: TestCagetoryEnum.values()){
            if ((categoryEnum.value & category) > 0){
                evnList.add(categoryEnum.name());
            }
        }
        return evnList;
    }

    public static List<Integer> getAllEnvValue(int category){
        List<Integer> evnList = new ArrayList<>();
        for (TestCagetoryEnum categoryEnum: TestCagetoryEnum.values()){
            if ((categoryEnum.value & category) > 0){
                evnList.add(categoryEnum.value);
            }
        }
        return evnList;
    }
}
