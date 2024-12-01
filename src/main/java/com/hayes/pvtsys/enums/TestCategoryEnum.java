package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum TestCategoryEnum {

    SIT_WEB(17),
    SIT_IPAD(33),
    UAT_WEB(18),
    UAT_IPAD(34),
    RT_WEB(20),
    RT_IPAD(36),
    PVT_WEB(24),
    PVT_IPAD(40);

    private final int value;

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
}
