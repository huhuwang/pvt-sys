package com.hayes.pvtsys.service;


import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.Deployment;
import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.query.DeploymentQuery;
import com.hayes.pvtsys.repository.DeploymentRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void addTickets(List<Ticket> tickets){
        ticketRepository.saveAll(tickets);
    }
}
