package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestDeviceEnum {

    WEB(16),

    IPAD(32);

    private final int value;
    public static String getDevice(int category){
        for (TestDeviceEnum categoryEnum: TestDeviceEnum.values()){
            if ((categoryEnum.value & category) > 0){
                return categoryEnum.name();
            }
        }
        return null;
    }
}
