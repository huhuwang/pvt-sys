package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.pojo.BaseTestCase;
import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.pojo.TestCase;
import com.hayes.pvtsys.query.BaseQuery;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.query.DeploymentQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BaseTicketCaseRepository extends JpaRepository<BaseTestCase, Integer> {
    @Query(value = "select b from BaseTestCase b  WHERE b.type = 2 order by b.id")
    Page<BaseTestCase> findPage(Pageable pageable);


    @Query("select b from BaseTestCase b " +
            " where b.id not in (select t.from from TestCase t where t.type = 2 and t.ticketNo = :ticketNo) ")
    List<BaseTestCase> queryBaseExcluding(@Param("ticketNo") String ticketNo);

}
