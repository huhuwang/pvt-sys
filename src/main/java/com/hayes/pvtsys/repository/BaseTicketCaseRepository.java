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

import java.util.Map;

public interface BaseTicketCaseRepository extends JpaRepository<BaseTestCase, Integer> {
    @Query(value = "select b from BaseTestCase b")
    Page<BaseTestCase> findPage(Pageable pageable);
}
