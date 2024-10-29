package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.service.DeploymentService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @PostMapping("/add")
    public HttpResult<Boolean> addDeployment(@RequestBody Deployment deployment){
        deploymentService.addDeployment(deployment);
        return HttpResult.returnSuccess(true);
    }
}
