package com.hayes.pvtsys.pojo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hayes.pvtsys.enums.TestCategoryEnum;
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
@Table(name = "common_test_case_base")
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseTestCase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "summary")
    private String summary;

    @Column(name = "expected_result")
    private String expectedResult;

    @Column(name = "category")
    private String category;

    @Column(name = "category_val")
    private Integer categoryVal;

    @Column(name = "type")
    private Integer type;

    @Column(name = "priority")
    private Byte priority;

    @Column(name = "row_height")
    private Integer rowHeight;

    @Column(name = "rt_flow")
    private String rtFlow;

    @Column(name = "step")
    private String step;

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

    public int rowHeight(){
        int summary = StrUtil.isBlank(this.summary) ? 1: this.summary.split("\n").length;
        int describe = StrUtil.isBlank(this.description) ? 1: this.description.split("\n").length;
        int expectedResult = StrUtil.isBlank(this.expectedResult) ? 1: this.expectedResult.split("\n").length;
        int max = Math.max(describe, summary);
        max = Math.max(expectedResult, max);
        return max;
    }

    public String getEnv(){
        return TestCategoryEnum.getEvnString(this.getCategory());
    }
}
