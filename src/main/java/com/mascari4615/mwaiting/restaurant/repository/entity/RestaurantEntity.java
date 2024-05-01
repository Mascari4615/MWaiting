package com.mascari4615.mwaiting.restaurant.repository.entity;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
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
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    @Column(name = "RESTAURANT_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Column
    private String restaurantName;

    @Column
    private String restaurantPhoneNumber;

    @Column
    private String restaurantAddress;

    @Column
    private String restaurantTime;

    @Column
    private String restaurantDescription;

    public static RestaurantEntity toRestaurantEntity(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurant = new RestaurantEntity();
        // restaurant.setMemberEntity(restaurantDTO.getMemberEntity());
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setRestaurantPhoneNumber(restaurantDTO.getRestaurantPhoneNumber());
        restaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
        restaurant.setRestaurantTime(restaurantDTO.getRestaurantTime());
        restaurant.setRestaurantDescription(restaurantDTO.getRestaurantDescription());
        return restaurant;
    }
}
