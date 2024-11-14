package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getAllDevice(int category){
        List<String> deviceList = new ArrayList<>();
        for (TestDeviceEnum categoryEnum: TestDeviceEnum.values()){
            if ((categoryEnum.value & category) > 0){
                deviceList.add(categoryEnum.name());
            }
        }
        return deviceList;
    }

    public static List<Integer> getAllDeviceValue(int category){
        List<Integer> deviceList = new ArrayList<>();
        for (TestDeviceEnum categoryEnum: TestDeviceEnum.values()){
            if ((categoryEnum.value & category) > 0){
                deviceList.add(categoryEnum.value);
            }
        }
        return deviceList;
    }
}
