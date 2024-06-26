package com.mascari4615.mwaiting.ticket.repository;

import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import com.mascari4615.mwaiting.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<List<Ticket>> findByUserId(Long userId);
    Optional<List<Ticket>> findByRestaurantId(Long restaurantId);
}
