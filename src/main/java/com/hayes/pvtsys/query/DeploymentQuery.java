package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DeploymentQuery extends BaseQuery {

    private String deploymentName;

    private String applicationName;

    private Date deploymentDateFrom;

    private Date deploymentDateEnd;
}
