package com.hayes.pvtsys.dto;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TestCaseDto implements Serializable {

    private Integer id;

    private String ticketNo;

    private String description;

    private String summary;

    private String expectedResult;

    private Integer type;

    private Byte priority;

    private List<Integer> category;

    private String rtFlow;

    private String step;

    public int rowHeight(){
       int describe = StrUtil.isBlank(this.description) ? 1: this.description.split("\n").length;
       int summary = StrUtil.isBlank(this.summary) ? 1: this.summary.split("\n").length;
       int expectedResult = StrUtil.isBlank(this.expectedResult) ? 1: this.expectedResult.split("\n").length;
       int max = Math.max(describe, summary);
       max = Math.max(expectedResult, max);
       return max;
    }
}
