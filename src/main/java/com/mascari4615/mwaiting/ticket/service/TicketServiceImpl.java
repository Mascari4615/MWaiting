package com.mascari4615.mwaiting.ticket.service;

import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.ticket.repository.TicketRepository;
import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public void save(TicketDTO ticketDTO) {
        Ticket ticket = Ticket.toTicket(ticketDTO);
        ticketRepository.save(ticket);
    }

    @Override
    public List<TicketDTO> findAll() {
        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(TicketDTO.toTicketDTO(ticket));
        }
        return ticketDTOList;
    }

    @Override
    public TicketDTO findById(Long id) {
        Optional<Ticket> ticketData = ticketRepository.findById(id);
        if (ticketData.isPresent()) {
            return TicketDTO.toTicketDTO(ticketData.get());
        }
        return null;
    }

    @Override
    public void setState(Long id, TicketState state) {
        // System.out.println(state);
        Optional<Ticket> ticketData = ticketRepository.findById(id);
        if (ticketData.isPresent()) {
            Ticket ticket = ticketData.get();
            ticket.setState(state);
            ticketRepository.save(ticket);
        }
    }

    // 특정 User의 Ticket 목록을 조회하는 메소드
    @Override
    public List<TicketDTO> findByUserId(Long userId) {
        Optional<List<Ticket>> ticketList = ticketRepository.findByUserId(userId);
        if (ticketList.isPresent()) {
            return ticketListToDTO(ticketList.get());
        }
        return null;
    }

    // 특정 Restaurant의 Ticket 목록을 조회하는 메소드
    @Override
    public List<TicketDTO> findByRestaurantId(Long restaurantId) {
        Optional<List<Ticket>> ticketList = ticketRepository.findByRestaurantId(restaurantId);
        if (ticketList.isPresent()) {
            return ticketListToDTO(ticketList.get());
        }
        return null;
    }

    private List<TicketDTO> ticketListToDTO(List<Ticket> ticketList) {
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(TicketDTO.toTicketDTO(ticket));
        }
        return ticketDTOList;
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}
