package com.mascari4615.mwaiting.restaurant.service;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantDTO;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantRegisterRequest;
import com.mascari4615.mwaiting.restaurant.repository.RestaurantRepository;
import com.mascari4615.mwaiting.restaurant.repository.entity.RestaurantEntity;
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
    public void save(RestaurantRegisterRequest restaurantRegisterRequest, MemberEntity memberEntity) {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .id(restaurantRegisterRequest.getId())
                .memberEntity(memberEntity)
                .restaurantName(restaurantRegisterRequest.getRestaurantName())
                .restaurantPhoneNumber(restaurantRegisterRequest.getRestaurantPhoneNumber())
                .restaurantAddress(restaurantRegisterRequest.getRestaurantAddress())
                .restaurantTime(restaurantRegisterRequest.getRestaurantTime())
                .restaurantDescription(restaurantRegisterRequest.getRestaurantDescription())
                .build();

        restaurantRepository.save(restaurantEntity); // 이건 이름 save 메소드여야 함
    }

    @Override
    public List<RestaurantDTO> findAll() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (RestaurantEntity restaurantEntity : restaurantEntityList) {
            restaurantDTOList.add(RestaurantDTO.toRestaurantDTO(restaurantEntity));
        }
        return restaurantDTOList;
    }

    public RestaurantDTO findById(Long id) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);
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
