package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.service.TicketService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add-ticket')")
    public HttpResult<Boolean> addTickets(@RequestBody List<Ticket> tickets){
        ticketService.addTickets(tickets);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/queryTicketByDeployment/{deploymentId}")
    @PreAuthorize("hasAnyAuthority('list-ticket')")
    public HttpResult<List<Ticket>> queryTicketByDeployment(@PathVariable("deploymentId") int deploymentId){
        List<Ticket> tickets = ticketService.queryTicketsByDeployment(deploymentId);
        return HttpResult.returnSuccess(tickets);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('delete-ticket')")
    public HttpResult<Boolean> deleteDeployment(@PathVariable("id") int id){
        ticketService.deleteTicket(id);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/query/{ticketNo}/{deploymentId}")
    @PreAuthorize("hasAnyAuthority('query-ticket-no')")
    public HttpResult<Ticket> queryTicketByTicketNo(@PathVariable("ticketNo") String ticketNo, @PathVariable("deploymentId") int deploymentId){
        Ticket ticket = ticketService.queryTicketByNo(ticketNo, deploymentId);
        return HttpResult.returnSuccess(ticket);
    }

    @GetMapping("/query/{id}")
    @PreAuthorize("hasAnyAuthority('query-ticket')")
    public HttpResult<Ticket> queryTicketByTicketId(@PathVariable("id") int id){
        Ticket ticket = ticketService.queryTicketById(id);
        return HttpResult.returnSuccess(ticket);
    }
}
