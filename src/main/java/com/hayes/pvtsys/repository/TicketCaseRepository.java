package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.pojo.TestCase;
import com.hayes.pvtsys.query.CaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TicketCaseRepository extends JpaRepository<TestCase, Integer> {

    @Query(value = "SELECT c.id as id, c.ticket_no as ticketNo, c.description as description, c.summary as summary, " +
                " c.expected_result as expectedResult, c.priority as priority, c.type as type, c.row_height as rowHeight, c.create_user as createUser, " +
                " r.update_user as updateUser, r.id as resultId, r.category as category, r.step as step, r.test_data as testData, r.actual_result as actualResult, r.result as result " +
                " from  test_result r INNER JOIN test_case c on c.id = r.case_id " +
                " WHERE c.ticket_no = :#{#query.ticketNo} and (r.category & :#{#query.env}) > 0 and (r.category & :#{#query.device}) > 0 order by c.create_time desc, r.id",
            countQuery = "SELECT count(*) from test_result r  INNER JOIN test_case c on c.id = r.case_id " +
            " WHERE c.ticket_no = :#{#query.ticketNo} and (r.category & :#{#query.env}) > 0 and (r.category & :#{#query.device}) > 0", nativeQuery = true)
    Page<Map<String, Object>> findPage(Pageable pageable, @Param("query") CaseQuery query);
}
