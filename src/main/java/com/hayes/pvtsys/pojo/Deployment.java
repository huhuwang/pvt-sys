package com.hayes.pvtsys.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "deployment")
@Data
public class Deployment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "deployment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deploymentDate;

    @Column(name = "deployment_name")
    private String deploymentName;

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "status")
    private byte status;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "remark")
    private String remark;

}
