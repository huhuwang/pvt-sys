package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findTicketByDeploymentIdOrderByCreateTimeDescIdDesc(Integer deploymentId);

    Ticket findTicketByTicketNoAndDeploymentId(String ticketNo, int deploymentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ticket t where t.deploymentId = :deploymentId")
    void deleteTicketByDeployment(@Param("deploymentId") int deploymentId);

}
