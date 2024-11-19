package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class DownloadQuery implements Serializable {

    private Integer deploymentId;

    private String ticketNo;

    private Integer env;
}
