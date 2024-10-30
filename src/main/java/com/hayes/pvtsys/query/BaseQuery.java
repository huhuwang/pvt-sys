package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseQuery implements Serializable {

    private Integer pageNum = 1;

    private Integer pageSize = 20;
}
