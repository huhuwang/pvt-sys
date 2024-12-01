package com.hayes.pvtsys.query;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class BaseCaseQuery extends BaseQuery {

    private Integer deploymentId;

    private String ticketNo;

    private Set<Integer> baseCaseIds;

    private List<Integer> env;

    private  List<Integer> device;

    private String description;
}
