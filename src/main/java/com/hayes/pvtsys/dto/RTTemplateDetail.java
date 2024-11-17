package com.hayes.pvtsys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class RTTemplateDetail implements Serializable {

    private Integer id;

    private String templateName;

    private Integer flowNumber;

    private String application;

    private byte status;

    private String createUser;

    List<Map<String,String>> rtFlow;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private String remark;
}
