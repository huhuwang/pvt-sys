package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.query.CaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketResultRepository extends JpaRepository<TestResult, Integer> {

    @Query(value = "SELECT r.id from test_result r INNER JOIN test_case c on c.id = r.case_id " +
            " WHERE c.ticket_no = :#{#query.ticketNo} and (r.category & :#{#query.env}) > 0 and (r.category & :#{#query.device}) > 0 order by c.create_time desc, r.id", nativeQuery = true)
    List<Integer> queryCaseId(@Param("query") CaseQuery query);
}
