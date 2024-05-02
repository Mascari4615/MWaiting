package com.mascari4615.mwaiting.ticket.controller;

import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.user.repository.UserRepository;
import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.RestaurantRepository;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;

    @PostMapping("/ticket/{restaurantID}")
    public String createTicket(@PathVariable Long restaurantID) {
        System.out.println("RestaurantController.save");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(restaurantID);
        Restaurant restaurant = restaurantEntity.get();
        System.out.println(restaurant);

        Optional<User> userEntity = userRepository.findByEmail(email);
        User user = userEntity.get();
        System.out.println(user);

        ticketService.save(restaurant, user);

        return "redirect:/";
    }

    @GetMapping("/ticket/{ticketId}/reject")
    public String rejectTicket(@PathVariable Long ticketId) {
        TicketDTO ticketDTO = ticketService.findById(ticketId);
        ticketService.setState(ticketId, TicketState.REJECTED);
        return "redirect:/restaurant-home/" + ticketDTO.getRestaurant().getId();
    }
}
