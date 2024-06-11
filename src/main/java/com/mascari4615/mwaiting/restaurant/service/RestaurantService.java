package com.mascari4615.mwaiting.restaurant.service;

import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantDTO;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    String registerRestaurant(RestaurantRegisterRequest restaurantRegisterRequest, String memberName);

    void save(RestaurantRegisterRequest restaurantRegisterRequest, User user);

    List<RestaurantDTO> findAll();

    RestaurantDTO findById(Long id);

    Optional<List<RestaurantDTO>> findByUserId(Long userId);
}
