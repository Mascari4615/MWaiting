package com.mascari4615.mwaiting.ticket.service;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import com.mascari4615.mwaiting.restaurant.repository.entity.RestaurantEntity;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;

import java.util.List;

public interface TicketService {
    void save(RestaurantEntity restaurantEntity, MemberEntity memberEntity);

    List<TicketDTO> findAll();

    TicketDTO findById(Long id);
}
