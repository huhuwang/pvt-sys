package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findTicketByDeploymentId(Integer deploymentId);

    Ticket findTicketByTicketNoAndDeploymentId(String ticketNo, int deploymentId);

    void deleteTicketByDeploymentId(@Param("deploymentId") int deploymentId);

}
