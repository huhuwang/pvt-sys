package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ExcelTitleEnum {

    TC_ID("TCID", 1500, 7),
    DESCRIPTION("Test Description", 10000, 3),
    SUMMARY("Summary", 10000, 4),
    ACTION("Action", 10000, 4),
    STEP("Step", 10000, 3),
    DATA("Data", 10000, 7),
    EXPECTED("Expected Result", 8000, 7),
    RESULT("Test Result", 2500, 3),
    ACTUAL_RESULT("Actual Result", 1500, 4),
    REMARK("REMARK", 10000, 4),
    EVIDENCE("Evidence", 18000, 7);



    private final String name;

    private final int width;

    private final int category;

    public static  List<ExcelTitleEnum> allEnv(int env){
        return all(env);
    }
    public static  List<ExcelTitleEnum> allRT(){
        return all(4);
    }

    private static List<ExcelTitleEnum> all(int env){
        List<ExcelTitleEnum> sit = new ArrayList<>();
        for (ExcelTitleEnum excelTitleEnum: ExcelTitleEnum.values()){
            if ((excelTitleEnum.category & env) > 0){
                sit.add(excelTitleEnum);
            }
        }
        return sit;
    }
}
