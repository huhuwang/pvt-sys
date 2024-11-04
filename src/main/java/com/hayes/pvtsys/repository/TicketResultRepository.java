package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.pojo.TestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketResultRepository extends JpaRepository<TestResult, Integer> {

}
