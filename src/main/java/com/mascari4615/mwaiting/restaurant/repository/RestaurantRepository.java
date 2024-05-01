package com.mascari4615.mwaiting.restaurant.repository;

import com.mascari4615.mwaiting.restaurant.repository.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    // Optional null방지
    Optional<RestaurantEntity> findById(long id);
}
