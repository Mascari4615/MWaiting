package com.mascari4615.mwaiting.restaurant.repository.entity;

import com.mascari4615.mwaiting.member.repository.entity.Member;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "restaurant_table")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    @Column(name = "RESTAURANT_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String time;

    @Column
    private String description;

    public static Restaurant toRestaurantEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        // restaurant.setMemberEntity(restaurantDTO.getMemberEntity());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setTime(restaurantDTO.getTime());
        restaurant.setDescription(restaurantDTO.getDescription());
        return restaurant;
    }
}
