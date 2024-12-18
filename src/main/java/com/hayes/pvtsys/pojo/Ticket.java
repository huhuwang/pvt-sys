package com.hayes.pvtsys.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ticket")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "deployment_id")
    private Integer deploymentId;

    @Column(name = "ticket_no")
    private String ticketNo;

    @Column(name = "ticket_title")
    private String ticketTitle;

    @Column(name = "ticket_url")
    private String ticketUrl;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "type")
    private byte type;

    @CreatedBy
    @Column(name = "create_user")
    private String createUser;

    @CreationTimestamp
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @LastModifiedBy
    @Column(name = "update_user")
    private String updateUser;

    @UpdateTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    private byte status;

}
