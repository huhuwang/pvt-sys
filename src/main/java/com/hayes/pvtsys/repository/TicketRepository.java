package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findTicketByDeploymentId(Integer deploymentId);
}
