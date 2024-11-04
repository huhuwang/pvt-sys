package com.hayes.pvtsys.dto;

import com.hayes.pvtsys.enums.TestCagetoryEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import lombok.Data;

@Data
public class TestResultDto {

    private Integer id;

    private String ticketNo;

    private String description;

    private String summary;

    private String expectedResult;

    private Byte priority;

    private Integer type;

    private Integer rowHeight;

    private String createUser;

    private String updateUser;

    private Integer resultId;

    private Integer category;

    private String step;

    private String testData;

    private String actualResult;

    private Byte result;

    private String env;

    private String device;

    public String getEnv() {
        return TestCagetoryEnum.getEvn(this.category);
    }

    public String getDevice() {
        return TestDeviceEnum.getDevice(this.category);
    }
}
