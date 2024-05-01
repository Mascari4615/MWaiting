package com.mascari4615.mwaiting.ticket.repository;

import com.mascari4615.mwaiting.ticket.repository.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
