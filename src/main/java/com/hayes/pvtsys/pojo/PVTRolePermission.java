package com.hayes.pvtsys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pvt_role_permission")
@Data
public class PVTRolePermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "principal_id")
    private Integer principalId;

    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "principal_type")
    private Integer principalType;

    @CreationTimestamp
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;


    @UpdateTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
