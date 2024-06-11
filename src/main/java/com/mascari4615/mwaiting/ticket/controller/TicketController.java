package com.mascari4615.mwaiting.ticket.controller;

import com.mascari4615.mwaiting.ticket.controller.DTO.TicketCreateDTO;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;

    @GetMapping("/ticket/{restaurantID}")
    public String createTicketForm(Model model, @PathVariable Long restaurantID) {
        // System.out.println("createTicketForm");

        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(restaurantID);
        Restaurant restaurant = restaurantEntity.get();
        // System.out.println(restaurant);

        model.addAttribute("restaurant", restaurant);

        List<TicketDTO> ticketsByRestaurant = ticketService.findByRestaurantId(restaurantID);
        int preTicketCount = 0;
        for (TicketDTO ticketDTO : ticketsByRestaurant) {
            if (ticketDTO.getState() == TicketState.WAITING) {
                preTicketCount++;
            }
        }
        model.addAttribute("preTicketCount", preTicketCount);

        return "ticket-create";
    }

    @PostMapping("/ticket/create")
    public String createTicket(@ModelAttribute TicketCreateDTO ticketCreateDTO) {
        // System.out.println("createTicket");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userData = userRepository.findByEmail(email);
        User user = userData.get();
        // System.out.println(user);

        Optional<Restaurant> restaurantData = restaurantRepository.findById(ticketCreateDTO.getRestaurantId());
        Restaurant restaurant = restaurantData.get();
        // System.out.println(restaurant);

        Long ticketNumber = 100L;
        List<TicketDTO> ticketDTOList = ticketService.findByRestaurantId(restaurant.getId());

        for (TicketDTO ticketDTO : ticketDTOList) {
            if (ticketDTO.getNumber() >= ticketNumber)
                ticketNumber = ticketDTO.getNumber() + 1;
        }

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setRestaurant(restaurant);
        ticketDTO.setUser(user);
        ticketDTO.setNumber(ticketNumber);
        ticketDTO.setHeadCount(ticketCreateDTO.getHeadCount());
        ticketDTO.setDescription(ticketCreateDTO.getDescription());
        ticketDTO.setState(TicketState.WAITING);

        ticketService.save(ticketDTO);

        return "redirect:/";
    }

    @PostMapping("/ticket/{ticketId}/reject")
    public String rejectTicket(@PathVariable Long ticketId) {
        TicketDTO ticketDTO = ticketService.findById(ticketId);
        ticketService.setState(ticketId, TicketState.REJECTED);
        return "redirect:/restaurant-home/" + ticketDTO.getRestaurant().getId();
    }

    @PostMapping("/ticket/{ticketId}/cancle")
    public String cancleTicket(@PathVariable Long ticketId) {
        TicketDTO ticketDTO = ticketService.findById(ticketId);
        ticketService.setState(ticketId, TicketState.CANCELED);
        return "redirect:/";
    }

    @GetMapping("/ticket/detail/{ticketId}")
    public String ticketDetail(@PathVariable Long ticketId, Model model) {
        TicketDTO targetTicket = ticketService.findById(ticketId);
        model.addAttribute("ticket", targetTicket);

        List<TicketDTO> ticketsByRestaurant = ticketService.findByRestaurantId(targetTicket.getRestaurant().getId());
        int preTicketCount = 0;

        for (TicketDTO ticketDTO : ticketsByRestaurant) {
            if (ticketDTO.getId() == targetTicket.getId())
                break;

            if (ticketDTO.getState() == TicketState.WAITING) {
                preTicketCount++;
            }
        }
        model.addAttribute("preTicketCount", preTicketCount);
        return "ticket-detail";
    }
}
