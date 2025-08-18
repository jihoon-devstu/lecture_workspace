package com.sinse.chatroomapp.controller;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import com.sinse.chatroomapp.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/member/loginform")
    public String loginForm(){
        return "member/login";
    }

    @GetMapping("/member/joinform")
    public String joinForm(){
        return "member/join";
    }

    @PostMapping("/member/regist")
    public String regist(Member member){
        memberService.insert(member);
        return "redirect:/member/loginform";
    }

    @PostMapping("/member/login")
    public String login(Member member, Model model){

        try {
            Member loginMember = memberService.login(member);
            if(loginMember != null){
                model.addAttribute("member",loginMember);
                return "member/result";
            }else{
                model.addAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다");
                return "member/login";
            }
        } catch (MemberException e) {
            e.printStackTrace();
            model.addAttribute("error", "로그인 처리 중 오류가 발생했습니다.");
            return "member/login";

        }

    }

}
