package com.mascari4615.mwaiting.member.controller;

import com.mascari4615.mwaiting.member.controller.dto.JoinRequest;
import com.mascari4615.mwaiting.member.controller.dto.MemberDTO;
import com.mascari4615.mwaiting.member.service.MemberService;
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
public class MemberController {
    // RestAPI
    // Representational State Transfer
    // HTTP Method : CRUD를 통해 표현한다
    // Stateless 모든 요청은 독립정인 요청이다

    // @GetMapping : 자원을 가져오는
    // @PutMapping
    // @PostMapping : 자원을 넣어주는
    // @DeleteMapping

    // 원래는 직접 만들어주고 해야하는데, 생성자 주입을 받는다
    private final MemberService memberService;

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

        // String result = memberService.join(id, name, phoneNumber);
        String result = memberService.join(joinRequest);

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
    @GetMapping("/member/register")
    public String registerForm() {
        return "member-register";
    }

    @PostMapping("/member/register")
    public String registerMember(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);

        memberService.save(memberDTO);

        return "index";
    }

//    @PostMapping("/member/save")
//    public String saveMember(
//            @RequestParam("memberEmail") String memberEmail,
//            @RequestParam("memberPassword") String memberPassword,
//            @RequestParam("memberName") String memberName)
//    {
//        System.out.println("MemberController.save");
//        System.out.println("memberEmail = " + memberEmail + ", memberPassword = " + memberPassword + ", memberName = " + memberName);
//        return "index";
//    }

    @GetMapping("/member/login")
    public String login() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("MemberController.login");
        System.out.println("memberDTO = " + memberDTO);
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            // 세션 : 정보 유지 (로그인 정보 유지)
            session.setAttribute("memberID", loginResult.getMemberEmail());
            System.out.println("memberID = " + loginResult.getMemberEmail());
            System.out.println("memberDTO = " + memberDTO);
            return "hello";
        } else {
            return "login";
        }
    }

    @GetMapping("/member/list")
    public String findAll(Model model) // 스프링에서 제공하는 객체 표현
    {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("memberList", memberDTOList);
        return "member-list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "member-detail";
    }
}