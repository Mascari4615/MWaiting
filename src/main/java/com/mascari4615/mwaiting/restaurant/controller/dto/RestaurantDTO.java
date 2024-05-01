package com.mascari4615.mwaiting.restaurant.controller.dto;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import com.mascari4615.mwaiting.restaurant.repository.entity.RestaurantEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestaurantDTO {
    private Long id;
    private MemberEntity memberEntity;
    private String restaurantName;
    private String restaurantPhoneNumber;
    private String restaurantAddress;
    private String restaurantTime;
    private String restaurantDescription;

    public static RestaurantDTO toRestaurantDTO(RestaurantEntity restaurantEntity) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurantEntity.getId());
        restaurantDTO.setMemberEntity(restaurantEntity.getMemberEntity());
        restaurantDTO.setRestaurantName(restaurantEntity.getRestaurantName());
        restaurantDTO.setRestaurantPhoneNumber(restaurantEntity.getRestaurantPhoneNumber());
        restaurantDTO.setRestaurantAddress(restaurantEntity.getRestaurantAddress());
        restaurantDTO.setRestaurantTime(restaurantEntity.getRestaurantTime());
        restaurantDTO.setRestaurantDescription(restaurantEntity.getRestaurantDescription());
        return restaurantDTO;
    }
}
