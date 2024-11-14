package com.hayes.pvtsys.query;

import lombok.Data;

@Data
public class CaseQuery extends BaseQuery{

    private String ticketNo;

    private Integer env;

    private Integer device;
}
