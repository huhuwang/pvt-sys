package com.hayes.pvtsys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * CREATE TABLE `pvt_permission` (
 *   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '注释',
 *   `permission_name` varchar(64) DEFAULT NULL COMMENT '权限名称',
 *   `permission_code` varchar(46) NOT NULL COMMENT '权限编号',
 *   `permission_url` varchar(128) DEFAULT NULL COMMENT '功能url',
 *   `permission_describe` varchar(128) DEFAULT NULL COMMENT '权限描述',
 *   `create_time` datetime DEFAULT NULL COMMENT '新建时间',
 *   `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
 *   PRIMARY KEY (`id`) USING BTREE,
 *   UNIQUE KEY `IDX_CODE` (`permission_code`) USING BTREE
 * ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 */

@Entity
@Table(name = "pvt_permission")
@Data
public class PVTPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_code")
    private String permissionCode;

    @Column(name = "permission_url")
    private String permissionUrl;

    @Column(name = "permission_describe")
    private String permissionDescribe;

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
