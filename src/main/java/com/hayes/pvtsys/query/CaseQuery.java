package com.hayes.pvtsys.query;

import lombok.Data;

@Data
public class CaseQuery extends BaseQuery{

    private String ticketNo;

    private int env;

    private int device;
}
