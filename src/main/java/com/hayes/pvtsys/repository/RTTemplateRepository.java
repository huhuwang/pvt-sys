package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.RTTemplate;
import com.hayes.pvtsys.query.RTTemplateQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RTTemplateRepository extends JpaRepository<RTTemplate, Integer> {

    @Query(value = "SELECT d from RTTemplate d WHERE (:#{#query.application} is null or :#{#query.application.size()} = 0 or d.application in (:#{#query.application})) " +
            " and (:#{#query.templateName} is null or d.templateName LIKE %:#{#query.templateName}%) ")
    Page<RTTemplate> findPage(Pageable pageable, @Param("query") RTTemplateQuery query);

    List<RTTemplate> findRTTemplateByApplicationAndStatusOrderByIdDesc(String application, Byte status);
}
