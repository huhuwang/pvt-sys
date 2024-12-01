package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum TestCategoryEnum {

    SIT_WEB(17, TestEnvEnum.SIT, TestDeviceEnum.WEB),
    SIT_IPAD(33, TestEnvEnum.SIT, TestDeviceEnum.IPAD),
    UAT_WEB(18, TestEnvEnum.UAT, TestDeviceEnum.WEB),
    UAT_IPAD(34, TestEnvEnum.UAT, TestDeviceEnum.IPAD),
    RT_WEB(20, TestEnvEnum.RT, TestDeviceEnum.WEB),
    RT_IPAD(36, TestEnvEnum.RT, TestDeviceEnum.IPAD),
    PVT_WEB(24, TestEnvEnum.PVT, TestDeviceEnum.WEB),
    PVT_IPAD(40, TestEnvEnum.PVT, TestDeviceEnum.IPAD);

    private final int value;

    private final TestEnvEnum env;

    private final TestDeviceEnum device;

    public static String getEvnString(String category){
        StringBuilder sb = new StringBuilder();
        List<Integer> list = Arrays.stream(category.split(",")).map(Integer::parseInt).toList();

        for (TestCategoryEnum categoryEnum: TestCategoryEnum.values()){
            if (list.contains(categoryEnum.value)) {
                sb.append(categoryEnum.name()).append(",");
            }
        }
        return sb.substring(0, sb.toString().length() - 1);
    }

    public static int getEnvInt(String category){
        Set<Integer> total = new HashSet<>();
        List<Integer> list = Arrays.stream(category.split(",")).map(Integer::parseInt).toList();
        for (TestCategoryEnum categoryEnum: TestCategoryEnum.values()){
            if (list.contains(categoryEnum.value)) {
                total.add(categoryEnum.env.getValue());
                total.add(categoryEnum.device.getValue());
            }
        }
       return total.stream().mapToInt(Integer::intValue).sum();
    }
}
