package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.service.TicketService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public HttpResult<Boolean> addTickets(@RequestBody List<Ticket> tickets){
        ticketService.addTickets(tickets);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/queryTicketByDeployment/{deploymentId}")
    public HttpResult<List<Ticket>> queryTicketByDeployment(@PathVariable("deploymentId") int deploymentId){

        List<Ticket> tickets = ticketService.queryTicketsByDeployment(deploymentId);
        return HttpResult.returnSuccess(tickets);
    }

    @DeleteMapping("/delete/{id}")
    public HttpResult<Boolean> deleteDeployment(@PathVariable("id") int id){
        ticketService.deleteTicket(id);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/query/{ticketNo}/{deploymentId}")
    public HttpResult<Ticket> queryTicketByTicketNo(@PathVariable("ticketNo") String ticketNo, @PathVariable("deploymentId") int deploymentId){
        Ticket ticket = ticketService.queryTicketByNo(ticketNo, deploymentId);
        return HttpResult.returnSuccess(ticket);
    }

    @GetMapping("/query/{id}")
    public HttpResult<Ticket> queryTicketByTicketId(@PathVariable("id") int id){
        Ticket ticket = ticketService.queryTicketById(id);
        return HttpResult.returnSuccess(ticket);
    }
}
