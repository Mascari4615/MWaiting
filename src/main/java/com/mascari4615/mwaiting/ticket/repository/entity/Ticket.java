package com.mascari4615.mwaiting.ticket.repository.entity;

import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "ticket_table")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column
    private Long number;

    @Column
    private Long headCount;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private TicketState state;

    public static Ticket toTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setRestaurant(ticketDTO.getRestaurant());
        ticket.setUser(ticketDTO.getUser());
        ticket.setNumber(ticketDTO.getNumber());
        ticket.setHeadCount(ticketDTO.getHeadCount());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setState(ticketDTO.getState());
        return ticket;
    }
}
