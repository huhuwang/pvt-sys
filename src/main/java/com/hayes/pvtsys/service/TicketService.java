package com.hayes.pvtsys.service;


import cn.hutool.core.io.FileUtil;
import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.repository.TicketCaseRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import com.hayes.pvtsys.util.ServerPath;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketCaseRepository ticketCaseRepository;

    public void addTickets(List<Ticket> tickets){
        for(Ticket ticket: tickets){
            ticket.setType((byte) 1);
            String path = ServerPath.outPath(ticket.getDeploymentId().toString(), ticket.getTicketNo());
            FileUtil.mkdir(path);
        }
        ticketRepository.saveAll(tickets);
    }

    public List<Ticket> queryTicketsByDeployment(int deploymentId){
       return ticketRepository.findTicketByDeploymentIdOrderByCreateTimeDescIdDesc(deploymentId);
    }

    @Transactional
    public void deleteTicket(int ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticketCaseRepository.deleteTestCaseByTicketNo(ticket.getTicketNo());
        ticketRepository.deleteById(ticketId);
    }

    public Ticket queryTicketByNo(String ticketNo, int deploymentId){
       return ticketRepository.findTicketByTicketNoAndDeploymentId(ticketNo, deploymentId);
    }

    public Ticket queryTicketById(int id){
        return ticketRepository.findById(id).orElseThrow();
    }
}
