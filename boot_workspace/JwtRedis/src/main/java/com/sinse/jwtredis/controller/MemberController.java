package com.sinse.jwtredis.controller;


import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.MemberDTO;
import com.sinse.jwtredis.dto.TokenResponseDTO;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RedisTokenService;
import com.sinse.jwtredis.model.member.RegistService;
import com.sinse.jwtredis.model.member.RegistServiceImpl;
import com.sinse.jwtredis.util.CookieUtil;
import com.sinse.jwtredis.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final RegistService registService;
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    //JWT 관련
    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;
    @Value("${app.jwt.access-minutes}") long accessMinutes;
    @Value("${app.jwt.refresh-days}") long refreshDays;

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

    //로그인 요청 처리
    //JWT 인증 후 없다면 발급.
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {

        //유효한 JWT 보유했는지 여부 먼저 따져보자.
        //따라서 DB에 회원이 존재하는지 여부를 판단...

        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPassword());

        //인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );

            //인증에 성공하면 AccessToken(값) != RefreshToken(값) - 재발급의 대상이 되는지 검증

            CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
            log.debug("인증받은 회원의 이름은 : " + userDetails.getUsername());
            log.debug("인증받은 회원의 이메일은 : " + userDetails.getEmail());
            log.debug("인증받은 회원의 비밀번호는 : " + userDetails.getPassword());
            log.debug("인증받은 회원의 권한은 : " + userDetails.getRoleName());

            //사용자 전역(모든 디바이스를 섭렵하므로)
            int userVersion = redisTokenService.currentUserVersion(userDetails.getUsername());

            //토큰 발급 !!
            //참고) 원래 디바이스 아이디는 디바이스마다 고유해야 하므로 , UUID를 적극 활용하자.
            String accessToken = jwtUtil.createAccessToken(userDetails.getUsername(),userVersion,memberDTO.getDeviceId());
            String refreshToken = jwtUtil.createRefreshToken(userDetails.getUsername(),memberDTO.getDeviceId());

            //Refresh 토큰을 보안 쿠키에 담기
            long rfTtlSec= refreshDays * (24*60*60);
            CookieUtil.setRefreshCooke(response,refreshToken,(int)rfTtlSec);


        //엑세스 토큰의 유효 시간
        long expSec = jwtUtil.parseToken(accessToken).getBody().getExpiration().toInstant().getEpochSecond();

        return  ResponseEntity.ok(new TokenResponseDTO(accessToken,expSec));
    }

    @GetMapping("/member/myinfo")
    public ResponseEntity<?> getMyInfo() {

        return ResponseEntity.ok("당신은 인증받은 회원입니다.");
    }

}
