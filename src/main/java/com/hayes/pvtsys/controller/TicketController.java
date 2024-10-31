package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.service.TicketService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
