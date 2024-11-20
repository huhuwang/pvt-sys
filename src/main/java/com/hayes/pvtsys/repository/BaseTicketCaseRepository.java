package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.pojo.BaseTestCase;
import com.hayes.pvtsys.query.BaseCaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaseTicketCaseRepository extends JpaRepository<BaseTestCase, Integer> {
    @Query(value = "select b.* from common_test_case_base b  WHERE b.type = 2 " +
            " and (b.category & :#{#query.env}) > 0 " +
            " and (b.category & :#{#query.device}) > 0 " +
            " and (:#{#query.description} is null or :#{#query.description} = '' or LOWER(b.description) like CONCAT('%',LOWER(:#{#query.description}), '%')) " +
            " order by b.id",
            countQuery = "select count(*) from common_test_case_base b  WHERE b.type = 2 " +
                    " and (b.category & :#{#query.env}) > 0 " +
                    " and (b.category & :#{#query.device}) > 0 " +
                    " and (:#{#query.description} is null or :#{#query.description} = '' or LOWER(b.description) like CONCAT('%',LOWER(:#{#query.description}), '%'))", nativeQuery = true)
    Page<BaseTestCase> findPage(Pageable pageable, @Param("query") BaseCaseQuery query);

    @Query(value = "select b.* from common_test_case_base b " +
            " where b.id not in (select t.base_case_from from test_case t where t.type = 2 and t.ticket_no = :#{#query.ticketNo}) " +
            " and (:#{#query.env} is null or b.category & :#{#query.env}) > 0 and (:#{#query.device} is null or b.category & :#{#query.device}) > 0"
            , nativeQuery = true)
    List<BaseTestCase> queryBaseExcluding(@Param("query") BaseCaseQuery query);


    @Query(value = "select b from BaseTestCase b " +
            " where b.id not in (select t.baseCaseFrom from TestCase t where t.type = 2 and t.ticketNo = :#{#query.ticketNo}) " +
            " and b.id in (:#{#query.baseCaseIds})")
    List<BaseTestCase> queryBase(@Param("query") BaseCaseQuery query);




}
