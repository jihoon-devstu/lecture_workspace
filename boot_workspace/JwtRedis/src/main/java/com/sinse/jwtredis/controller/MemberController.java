package com.sinse.jwtredis.controller;


import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.MemberDTO;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RegistService;
import com.sinse.jwtredis.model.member.RegistServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final RegistService registService;
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/member/regist")
    public ResponseEntity<?> registMember(@RequestBody MemberDTO memberDTO) {
        log.debug("regist member : "+ memberDTO);
        //registService.regist(memberDTO);

        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());

        memberService.regist(member);

        return ResponseEntity.ok(null);
    }
    
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPassword());

        //인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );

        if (authentication != null) {
            //인증에 성공하면 AccessToken(값) != RefreshToken(값) - 재발급의 대상이 되는지 검증

        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        log.debug("인증받은 회원의 이름은 : " + userDetails.getUsername());
        log.debug("인증받은 회원의 이메일은 : " + userDetails.getEmail());
        log.debug("인증받은 회원의 비밀번호는 : " + userDetails.getPassword());

        return  ResponseEntity.ok("로그인 성공");
    }

}
