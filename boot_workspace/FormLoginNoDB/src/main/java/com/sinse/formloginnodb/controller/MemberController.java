package com.sinse.formloginnodb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    /*
    스프링 시큐리티가 기본적으로 제공하는 폼 로그인 기능에서는 , 로그인 성공 시
    Root 로 Redirect 되어 있기 때문에 , 로그인 성공 후 보여질 내용이 있다면
    Controller에서 매핑을 처리해야 함.

    스프링 시큐리티에 의해 인증이 성공되면 , 사용자 정보는 UserDetails 객체에 들어있음.
    이때 , User Details 객체를 꺼내는 방법은 총 4가지 방법이 있음.
     */

    /* ---------------------------------------------------------------------------------------------
     방법 1) 세션에서 꺼내기
     --------------------------------------------------------------------------------------------- */

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        SecurityContext context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");

        Authentication auth = context.getAuthentication();
        UserDetails userDetails = (UserDetails)auth.getPrincipal();

        String id = userDetails.getUsername();
        model.addAttribute("id", "세션에서 꺼냄"+ id);

        return "member/index";
    }

    /* ---------------------------------------------------------------------------------------------
     방법 2) Authentication에서 직접 꺼내기
     --------------------------------------------------------------------------------------------- */

    @GetMapping("/main")
    public String main(Authentication auth, Model model) {
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        String id = userDetails.getUsername();
        model.addAttribute("id", "Authentication에서 꺼냄"+ id);

        return "member/index";
    }


    /* ---------------------------------------------------------------------------------------------
     방법 3) SecurityContextHolder에서 직접 꺼내기
     --------------------------------------------------------------------------------------------- */

    @GetMapping("/home")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String id = userDetails.getUsername();
        model.addAttribute("id","SecurityContextHolder에서 꺼냄"+id);
        return "member/index";
    }


        /* ---------------------------------------------------------------------------------------------
     방법 4) AuthenticationPrincipal 어노테이션 활용하기
     --------------------------------------------------------------------------------------------- */
    @GetMapping("/default")
    public String defaultPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("id","@AuthenticationPrincipal 에서 꺼냄" +  userDetails.getUsername());

        return "member/index";
    }
}
