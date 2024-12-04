package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.query.CaseQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketResultRepository extends JpaRepository<TestResult, Integer> {

    @Query(value = "SELECT r.id from test_result r INNER JOIN test_case c on c.id = r.case_id " +
            " WHERE c.ticket_no = :#{#query.ticketNo} and (r.category & :#{#query.env}) > 0 and (r.category & :#{#query.device}) > 0 order by c.create_time, r.id", nativeQuery = true)
    List<Integer> queryCaseId(@Param("query") CaseQuery query);

    @Modifying
    @Transactional
    @Query(value = "delete from TestResult r where r.testCase.id = :caseId")
    void deleteTestResultByTestCaseId(@Param("caseId") int caseId);

    @Query(value = "select count(*) from TestResult t where t.testCase.id = :caseId")
    int countByCase(@Param("caseId") int caseId);

    @Query(value = "select t from TestResult t where t.testCase.ticketNo = :ticketNo AND t.category = :evn")
    List<TestResult> findTestResultByEnvAndTicket(@Param("ticketNo") String ticketNo, @Param("evn") Integer evn);

    @Query(value = "select t from TestResult t where t.testCase.ticketNo in (:ticketNo) AND t.category in (20, 36) AND t.rtFlow = :RT")
    List<TestResult> findTestResultByEnvAndTicketWithRT(@Param("ticketNo") List<String> ticketNo,  @Param("RT") String RT);


    @Query(value = "select t from TestResult t where t.testCase.ticketNo in (:ticketNo) AND t.category in (24, 40)")
    List<TestResult> findTestResultByEnvAndTicketWithPVT(@Param("ticketNo") List<String> ticketNo);

    @Query(value = "select t from TestResult t where t.testCase.id = :caseId")
    List<TestResult> findTestResultByCaseId(@Param("caseId") Integer caseId);
}
