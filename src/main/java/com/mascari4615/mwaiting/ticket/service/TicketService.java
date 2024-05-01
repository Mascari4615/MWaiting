package com.mascari4615.mwaiting.ticket.service;

import com.mascari4615.mwaiting.member.repository.entity.Member;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;

import java.util.List;

public interface TicketService {
    void save(Restaurant restaurant, Member member);

    List<TicketDTO> findAll();

    TicketDTO findById(Long id);
}
