package com.hayes.pvtsys.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class RelatedCaseDto implements Serializable {

    private int deploymentId;

    private Set<Integer> caseIds;
}
