package com.mascari4615.mwaiting.restaurant.repository;

import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import com.mascari4615.mwaiting.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findById(long id);
    Optional<List<Restaurant>> findByUserId(Long userId);
}
