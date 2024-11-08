package com.hayes.pvtsys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "document")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Document implements Serializable {

    @Id
    @Column
    private String id;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "result_id")
    private Integer resultId;

    @Column(name = "description")
    private String description;

    @Column(name = "original_size")
    private Long originalSize;

    @Column(name = "scale_size")
    private Long scaleSize;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "url")
    private String url;

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
