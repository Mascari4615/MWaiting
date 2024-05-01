package com.mascari4615.mwaiting.restaurant.service;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantDTO;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantRegisterRequest;

import java.util.List;

public interface RestaurantService {
    String registerRestaurant(RestaurantRegisterRequest restaurantRegisterRequest, String memberName);

    void save(RestaurantRegisterRequest restaurantRegisterRequest, MemberEntity memberEntity);

    List<RestaurantDTO> findAll();

    RestaurantDTO findById(Long id);
}
