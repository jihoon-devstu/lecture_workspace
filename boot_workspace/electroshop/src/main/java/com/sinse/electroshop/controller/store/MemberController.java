package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.MemberDto;
import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.exception.MemberNotFoundException;
import com.sinse.electroshop.model.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/shop/loginform")
    public String loginForm() {
        return "electro/login";
    }

    @ResponseBody
    @PostMapping("/shop/login")
    public ResponseEntity<String> login(MemberDto  memberDto, HttpSession session) {
        Member member = new Member();
        member.setId(memberDto.getMemberId());
        member.setPassword(memberDto.getMemberPwd());
        Member loggedInMember = memberService.login(member);
        session.setAttribute("member", loggedInMember);

        memberDto.setMemberPwd(null);
        memberDto.setName(loggedInMember.getName());
        // 로그인 성공/실패 여부에 따라 응답 처리
            return ResponseEntity.status(HttpStatus.OK).body(memberDto.toString() );
    }
    //이 컨트롤러 내에서 발생하는 모든 예외를 처리할 수 있다.

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
