package com.mascari4615.mwaiting.ticket.controller.DTO;

import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import com.mascari4615.mwaiting.user.repository.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketDTO {
    private Long id;
    private Restaurant restaurant;
    private User user;
    private Long ticketNumber;

    public static TicketDTO toTicketDTO(Ticket ticket)
    {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setRestaurant(ticket.getRestaurant());
        ticketDTO.setUser(ticket.getUser());
        ticketDTO.setTicketNumber(ticket.getTicketNumber());
        return ticketDTO;
    }
}
