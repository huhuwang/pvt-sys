package com.hayes.pvtsys.service;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.query.DeploymentQuery;
import com.hayes.pvtsys.repository.DeploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class DeploymentService {

    @Autowired
    private DeploymentRepository deploymentRepository;

    public void addDeployment(Deployment deployment){
        deployment.setCreateTime(new Date());
        deployment.setCreateUser("Hayes");
        deployment.setStatus((byte) 1);
        deploymentRepository.save(deployment);
    }

    public void deleteDeployment(Integer id){
        deploymentRepository.deleteById(id);
    }

    public PageResponse<Deployment> findPage(DeploymentQuery query){

        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize(), Sort.by(Sort.Direction.DESC, "createTime", "id"));
        Page<Deployment> deployments = deploymentRepository.findPage(pageRequest, query);
        return new PageResponse<>(deployments);
    }
}
