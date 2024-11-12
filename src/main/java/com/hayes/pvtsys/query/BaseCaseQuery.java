package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class BaseCaseQuery implements Serializable {

    private String ticketNo;

    private Set<Integer> baseCaseIds;
}
