package com.mascari4615.mwaiting.user.controller;

import com.mascari4615.mwaiting.user.controller.dto.JoinRequest;
import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import com.mascari4615.mwaiting.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    // RestAPI
    // Representational State Transfer
    // HTTP Method : CRUD를 통해 표현한다
    // Stateless 모든 요청은 독립정인 요청이다

    // @GetMapping : 자원을 가져오는
    // @PutMapping
    // @PostMapping : 자원을 넣어주는
    // @DeleteMapping

    // 원래는 직접 만들어주고 해야하는데, 생성자 주입을 받는다
    private final UserService userService;

    // Notation
    // @RequestMapping("/hello") -> Method를 지정하지 않으면 모든 Method에 대해 응답
    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinRequest joinRequest) {
        String id = joinRequest.getId();
        String name = joinRequest.getName();
        String phoneNumber = joinRequest.getPhoneNumber();

        // String result = userService.join(id, name, phoneNumber);
        String result = userService.join(joinRequest);

        // if (result.equalsIgnoreCase("success"))
        if ("success".equalsIgnoreCase(result)) // Null Exception 방지
        {
            return "success";
        } else {
            return "fail";
        }

        // Talend API Tester
        // @valid, @validated
        // exceptionhandler, controlleradive
        // restTemplate, webClient
        // 탈퇴, 휴먼
    }

    // 회원가입 페이지 출력 요청
    @GetMapping("/user/register")
    public String registerForm() {
        return "user-register";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        System.out.println("UserController.save");
        System.out.println("userDTO = " + userDTO);

        userService.save(userDTO);

        return "index";
    }

//    @PostMapping("/user/save")
//    public String saveUser(
//            @RequestParam("userEmail") String userEmail,
//            @RequestParam("userPassword") String userPassword,
//            @RequestParam("userName") String userName)
//    {
//        System.out.println("UserController.save");
//        System.out.println("userEmail = " + userEmail + ", userPassword = " + userPassword + ", userName = " + userName);
//        return "index";
//    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {
        System.out.println("UserController.login");
        System.out.println("userDTO = " + userDTO);
        UserDTO loginResult = userService.login(userDTO);

        if (loginResult != null) {
            // 세션 : 정보 유지 (로그인 정보 유지)
            session.setAttribute("userID", loginResult.getEmail());
            System.out.println("userID = " + loginResult.getEmail());
            System.out.println("userDTO = " + userDTO);
            return "hello";
        } else {
            return "login";
        }
    }

    @GetMapping("/user/list")
    public String findAll(Model model) // 스프링에서 제공하는 객체 표현
    {
        List<UserDTO> userDTOList = userService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("userList", userDTOList);
        return "user-list";
    }

    @GetMapping("/user/{id}")
    public String findById(@PathVariable Long id, Model model) {
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("user", userDTO);
        return "user-detail";
    }
}