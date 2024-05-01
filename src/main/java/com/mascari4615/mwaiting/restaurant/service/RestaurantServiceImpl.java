package com.mascari4615.mwaiting.restaurant.service;

import com.mascari4615.mwaiting.member.repository.entity.Member;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantDTO;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantRegisterRequest;
import com.mascari4615.mwaiting.restaurant.repository.RestaurantRepository;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public String registerRestaurant(RestaurantRegisterRequest restaurantRegisterRequest, String memberName) {
        return "";
    }

    @Override
    public void save(RestaurantRegisterRequest restaurantRegisterRequest, Member member) {
        Restaurant restaurant = Restaurant.builder()
                .id(restaurantRegisterRequest.getId())
                .member(member)
                .name(restaurantRegisterRequest.getName())
                .phoneNumber(restaurantRegisterRequest.getPhoneNumber())
                .address(restaurantRegisterRequest.getAddress())
                .time(restaurantRegisterRequest.getTime())
                .description(restaurantRegisterRequest.getDescription())
                .build();

        restaurantRepository.save(restaurant); // 이건 이름 save 메소드여야 함
    }

    @Override
    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            restaurantDTOList.add(RestaurantDTO.toRestaurantDTO(restaurant));
        }
        return restaurantDTOList;
    }

    public RestaurantDTO findById(Long id) {
        Optional<Restaurant> optionalRestaurantEntity = restaurantRepository.findById(id);
        // return restaurantEntity.map(RestaurantDTO::toRestaurantDTO).orElse(null);

        if (optionalRestaurantEntity.isPresent()) {
//            RestaurantEntity restaurantEntity = optionalRestaurantEntity.get();
//            RestaurantDTO restaurantDTO = RestaurantDTO.toRestaurantDTO(restaurantEntity);
//            return restaurantDTO;
            return RestaurantDTO.toRestaurantDTO(optionalRestaurantEntity.get());
        } else {
            return null;
        }
    }
}
