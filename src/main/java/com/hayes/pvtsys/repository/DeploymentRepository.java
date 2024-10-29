package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DeploymentRepository extends JpaRepository<Deployment, Integer> {

    List<Deployment> findDeploymentsByApplicationName(String applicationName);

    /*@Query("SELECT d from Deployment d WHERE d.deploymentName LIKE '%:deploymentName%'")
    List<Deployment> queryDeploymentsByDeploymentName(@Param("deploymentName") String deploymentName);*/

    List<Deployment> findDeploymentsByDeploymentNameContains(String deploymentName);

    List<Deployment> findDeploymentsByDeploymentDateBetween(Date after, Date before);
}
