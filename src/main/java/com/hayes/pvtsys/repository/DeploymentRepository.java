package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.query.DeploymentQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeploymentRepository extends JpaRepository<Deployment, Integer> {

    @Query(value = "SELECT d from Deployment d WHERE (:#{#query.applicationName} is null or d.applicationName = :#{#query.applicationName}) " +
            " and (:#{#query.deploymentName} is null or d.deploymentName LIKE %:#{#query.deploymentName}%) " +
            " and (:#{#query.deploymentDateFrom} is null or d.deploymentDate >= :#{#query.deploymentDateFrom}) " +
            " and (:#{#query.deploymentDateEnd} is null or d.deploymentDate <= :#{#query.deploymentDateEnd})")
    Page<Deployment> findPage(Pageable pageable, @Param("query") DeploymentQuery query);
}
