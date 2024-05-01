package com.mascari4615.mwaiting.restaurant.controller.dto;

import lombok.Data;

@Data

public class RestaurantRegisterRequest {
    private Long id;
    private String restaurantName;
    private String restaurantPhoneNumber;
    private String restaurantAddress;
    private String restaurantTime;
    private String restaurantDescription;
}
