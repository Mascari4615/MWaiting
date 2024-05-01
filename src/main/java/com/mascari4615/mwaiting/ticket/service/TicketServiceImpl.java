package com.mascari4615.mwaiting.ticket.service;

import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.ticket.repository.TicketRepository;
import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public void save(Restaurant restaurant, User user) {
        Ticket ticket = Ticket.builder()
                .user(user)
                .restaurant(restaurant)
                .ticketNumber(100L)
                .build();

        ticketRepository.save(ticket); // 이건 이름 save 메소드여야 함
    }

    @Override
    public List<TicketDTO> findAll() {
        return List.of();
    }

    @Override
    public TicketDTO findById(Long id) {
        return null;
    }
}
