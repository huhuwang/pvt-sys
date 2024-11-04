package com.hayes.pvtsys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hayes.pvtsys.dto.TestResultDto;
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
@Table(name = "test_result")
@Data
@EntityListeners(AuditingEntityListener.class)
/*@NamedNativeQuery(
        name = "TestResult.findPage",
        query = "SELECT c.id as id, c.ticket_no as ticketNo, c.description as description, c.summary as summary, " +
                " c.expected_result as expectedResult, c.priority as priority, c.type as type, c.row_height as rowHeight, c.create_user as createUser, " +
                " r.update_user as updateUser, r.id as resultId, r.category as category, r.step as step, r.test_data as testData, r.actual_result as actualResult, r.result as result " +
                " from test_result r INNER JOIN  test_case c on c.id = r.case_id " +
                " WHERE r.category & ?1 > 0 order by c.create_time desc, r.id",
        resultSetMapping = "testResultDtoMapping")

@SqlResultSetMapping(name = "testResultDtoMapping", entities = {
        @EntityResult(entityClass = TestResult.class, fields = {
                @FieldResult(name = "id", column = "id"),
                @FieldResult(name = "ticketNo", column = "ticketNo"),
                @FieldResult(name = "description", column = "description"),
                @FieldResult(name = "summary", column = "summary"),
                @FieldResult(name = "expectedResult", column = "expectedResult"),
                @FieldResult(name = "priority", column = "priority"),
                @FieldResult(name = "type", column = "type"),
                @FieldResult(name = "rowHeight", column = "rowHeight"),
                @FieldResult(name = "createUser", column = "createUser"),
                @FieldResult(name = "updateUser", column = "updateUser"),
                @FieldResult(name = "resultId", column = "resultId"),
                @FieldResult(name = "category", column = "category"),
                @FieldResult(name = "step", column = "step"),
                @FieldResult(name = "testData", column = "testData"),
                @FieldResult(name = "actualResult", column = "actualResult"),
                @FieldResult(name = "result", column = "result"),
        })
})*/
public class TestResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "case_id")
    private Integer caseId;

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
}
