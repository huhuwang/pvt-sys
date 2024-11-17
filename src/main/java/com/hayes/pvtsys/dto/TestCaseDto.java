package com.hayes.pvtsys.dto;

import cn.hutool.core.util.StrUtil;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class TestCaseDto implements Serializable {

    private String ticketNo;

    private String description;

    private String summary;

    private String expectedResult;

    private Integer type;

    private Byte priority;

    private Integer category;

    private String rtFlow;

    public int rowHeight(){
       int describe = StrUtil.isBlank(this.description) ? 1: this.description.split("\n").length;
       int summary = StrUtil.isBlank(this.summary) ? 1: this.summary.split("\n").length;
       int expectedResult = StrUtil.isBlank(this.expectedResult) ? 1: this.expectedResult.split("\n").length;
       int max = Math.max(describe, summary);
       max = Math.max(expectedResult, max);
       return max;
    }
}
