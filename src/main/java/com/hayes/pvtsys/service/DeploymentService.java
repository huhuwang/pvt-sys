package com.hayes.pvtsys.service;


import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.repository.DeploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeploymentService {

    @Autowired
    private DeploymentRepository deploymentRepository;

    public Deployment addDeployment(Deployment deployment){
        deployment.setCreateTime(new Date());
        deployment.setCreateUser("Hayes");
        deployment.setStatus((byte) 1);
        return deploymentRepository.save(deployment);
    }
}
