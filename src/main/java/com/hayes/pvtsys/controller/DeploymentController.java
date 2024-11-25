package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.query.DeploymentQuery;
import com.hayes.pvtsys.service.DeploymentService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add-deployment')")
    public HttpResult<Boolean> addDeployment(@RequestBody Deployment deployment){
        deploymentService.addDeployment(deployment);
        return HttpResult.returnSuccess(true);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('delete-deployment')")
    public HttpResult<Boolean> deleteDeployment(@PathVariable("id") int id){
        deploymentService.deleteDeployment(id);
        return HttpResult.returnSuccess(true);
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyAuthority('list-deployment')")
    public HttpResult<PageResponse<Deployment>> pageDeployment(@RequestBody DeploymentQuery query){
        PageResponse<Deployment> page = deploymentService.findPage(query);
        return HttpResult.returnSuccess(page);
    }

    @GetMapping("/query/{id}")
    @PreAuthorize("hasAnyAuthority('query-deployment')")
    public HttpResult<Deployment> queryDeploymentId(@PathVariable("id") int deploymentId){
        Deployment deployment = deploymentService.queryById(deploymentId);
        return HttpResult.returnSuccess(deployment);
    }
}
