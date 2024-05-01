package com.mascari4615.mwaiting.ticket.controller;

import com.mascari4615.mwaiting.member.repository.MemberRepository;
import com.mascari4615.mwaiting.member.repository.entity.Member;
import com.mascari4615.mwaiting.restaurant.repository.RestaurantRepository;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final TicketService ticketService;

    @PostMapping("/ticket/{restaurantID}")
    public String createTicket(@SessionAttribute(name = "memberID", required = false) String memberID, @PathVariable Long restaurantID) {
        System.out.println("RestaurantController.save");
        System.out.println("memberID = " + memberID);

        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(restaurantID);
        Restaurant restaurant = restaurantEntity.get();
        System.out.println(restaurant);

        Optional<Member> memberEntity = memberRepository.findByEmail(memberID);
        Member member = memberEntity.get();
        System.out.println(member);

        ticketService.save(restaurant, member);

        return "redirect:/";
    }
}
