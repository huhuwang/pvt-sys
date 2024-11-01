package com.hayes.pvtsys.service;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.query.DeploymentQuery;
import com.hayes.pvtsys.repository.DeploymentRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private TicketRepository ticketRepository;

    public void addDeployment(Deployment deployment){
        deploymentRepository.save(deployment);
    }

    @Transactional
    public void deleteDeployment(Integer id){
        ticketRepository.deleteTicketByDeployment(id);
        deploymentRepository.deleteById(id);
    }

    public PageResponse<Deployment> findPage(DeploymentQuery query){

        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize(), Sort.by(Sort.Direction.DESC, "createTime", "id"));
        Page<Deployment> deployments = deploymentRepository.findPage(pageRequest, query);
        return new PageResponse<>(deployments);
    }
}
