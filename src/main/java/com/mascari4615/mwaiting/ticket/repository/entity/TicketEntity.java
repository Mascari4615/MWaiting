package com.mascari4615.mwaiting.ticket.repository.entity;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import com.mascari4615.mwaiting.restaurant.repository.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "ticket_table")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID")
    private RestaurantEntity restaurantEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Column
    private Long ticketNumber;
}
