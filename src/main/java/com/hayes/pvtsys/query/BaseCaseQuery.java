package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class BaseCaseQuery implements Serializable {

    private Integer deploymentId;

    private String ticketNo;

    private Set<Integer> baseCaseIds;

    private Integer env;

    private Integer device;
}
