package com.hayes.pvtsys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hayes.pvtsys.enums.TestCagetoryEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "test_result")
@Data
@EntityListeners(AuditingEntityListener.class)
public class TestResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private TestCase testCase;

    @Column(name = "category")
    private Integer category;

    @Column(name = "step")
    private String step;

    @Column(name = "test_data")
    private String testData;

    @Column(name = "actual_result")
    private String actualResult;

    @Column(name = "result")
    private Byte result;

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

    @OneToMany
    @JoinColumn(name="result_id")
    private List<Document> documents;

    public String getEnv() {
        return TestCagetoryEnum.getEvn(this.category);
    }

    public String getDevice() {
        return TestDeviceEnum.getDevice(this.category);
    }
}
