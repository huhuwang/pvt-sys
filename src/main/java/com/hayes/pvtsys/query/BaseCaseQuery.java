package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class BaseCaseQuery extends BaseQuery {

    private Integer deploymentId;

    private String ticketNo;

    private Set<Integer> baseCaseIds;

    private Integer env;

    private Integer device;

    private String description;
}
