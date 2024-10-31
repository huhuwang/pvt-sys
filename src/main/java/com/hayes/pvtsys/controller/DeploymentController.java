package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.query.DeploymentQuery;
import com.hayes.pvtsys.service.DeploymentService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{id}")
    public HttpResult<Boolean> deleteDeployment(@PathVariable("id") int id){
        deploymentService.deleteDeployment(id);
        return HttpResult.returnSuccess(true);
    }

    @PostMapping("/page")
    public HttpResult<PageResponse<Deployment>> pageDeployment(@RequestBody DeploymentQuery query){
        PageResponse<Deployment> page = deploymentService.findPage(query);
        return HttpResult.returnSuccess(page);
    }
}
