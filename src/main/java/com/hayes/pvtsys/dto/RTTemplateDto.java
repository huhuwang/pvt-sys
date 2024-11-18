package com.hayes.pvtsys.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class RTTemplateDto implements Serializable {

    private Integer id;

    private String templateName;

    private Integer flowNumber;

    private String application;

    List<Map<String,String>> rtFlow;

}
