package com.hayes.pvtsys.query;

import lombok.Data;

import java.util.List;

@Data
public class RTTemplateQuery extends BaseQuery {

    private String templateName;

    private List<String> application;
}
