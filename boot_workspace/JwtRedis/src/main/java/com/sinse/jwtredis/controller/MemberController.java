package com.sinse.jwtredis.controller;


import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.LogoutRequest;
import com.sinse.jwtredis.dto.MemberDTO;
import com.sinse.jwtredis.dto.TokenResponseDTO;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RedisTokenService;
import com.sinse.jwtredis.model.member.RegistService;
import com.sinse.jwtredis.model.member.RegistServiceImpl;
import com.sinse.jwtredis.util.CookieUtil;
import com.sinse.jwtredis.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

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

        //refresh 토큰의 경우 서버에 저장해 놓아야 , 추후 재발급시 클라이언트가 전송한 쿠키에 들어있는
        // refresh 토큰과 비교가 가능하므로 , redis 에 저장하자
        redisTokenService.saveRefreshToken(userDetails.getUsername(),memberDTO.getDeviceId(),refreshToken,rfTtlSec);

        //엑세스 토큰의 유효 시간
        long expSec = jwtUtil.parseToken(accessToken).getBody().getExpiration().toInstant().getEpochSecond();

        //long expSec=60;
        return  ResponseEntity.ok(new TokenResponseDTO(accessToken,expSec));
    }

    @GetMapping("/member/myinfo")
    public ResponseEntity<?> getMyInfo() {

        return ResponseEntity.ok("당신은 인증받은 회원입니다.");
    }

    //토큰 재발급 요청 처리
    /*
    *  @CookieValue(value="쿠키명", required=true/false) 자료형 변수명
    *  클라이언트의 요청 헤더에 포함된 Cookie 항목에서 특정 쿠키 이름을 찾아 컨트롤러 메서드의 파라미터에 주입
    * required = true : 해당 쿠키가 없으면 400 에러 (Bad Request)
    * required = false : 쿠키가 없어도 예외가 발생하지 않음 , 그냥 null이 들어옴.
    * */
    @PostMapping("/member/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(value="refresh", required= false) String refreshToken,@RequestBody MemberDTO memberDTO,HttpServletResponse response ) {
        try{
            //쿠키가 없다면 401 에러 보내기
            if(!StringUtils.hasText(refreshToken)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of("error","no refresh token found"));
            }
            //필수는 아니지만 , 한명의 유저가 보유한 여러 디바이스 관련 인증 처리할 경우 deviceId
            //파라미터가 DTO가 아니므로 별도 처리 불필요...

            //재발급에 앞서 , Refresh Token 이 유효한지를 검증하자
            Jws<Claims> jws =  jwtUtil.parseToken(refreshToken);
            Claims claims = jws.getBody();
            String username = claims.getSubject();
            String deviceId = memberDTO.getDeviceId();

            //redis와 일치여부 판단
            if(!redisTokenService.matchesRefreshToken(username, deviceId, refreshToken )){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error","no refresh token not matched"));
            };

            //redis에서 관리중인 userVersion 가져오기
            int version = redisTokenService.currentUserVersion(username);

            //보안상 안정성을 위해 AccessToken만 발급하지 말고 , RefreshToken 조차 갱신하는게 좋다 !!!
            String newAccessToken = jwtUtil.createAccessToken(username,version,deviceId);
            String newRefreshToken = jwtUtil.createRefreshToken(username,deviceId);

            //RefreshToken 새롭게 발급되었으므로 , 기존 redis 가 보관하고 있던 refreshToken 을 제거 및 신규 토큰 넣기
            redisTokenService.deleteRefreshToken(username,deviceId);
            long rtTtlSec= refreshDays * (24*60*60);
            redisTokenService.saveRefreshToken(username,deviceId,newRefreshToken,rtTtlSec);

            //보안처리된 쿠키에 리프레쉬 토큰 담기
            CookieUtil.setRefreshCooke(response,newRefreshToken,(int)rtTtlSec);

            //원래 목적이었던 AccessToken을 응답 body에 넣기
            return ResponseEntity.ok(Map.of("accessToken",newAccessToken));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error","요청 실패"));
        }

    }

    /*로그아웃 요청 처리

        1) 로그아웃 요청하는 클라이언트의 AccessToken을 블랙리스트 등록 !
        2) 회원으로써 서비스 이용을 중단 요청이기에 , Redis에 등록된 RefreshToken 삭제
        3) 쿠키에 남아있는 Refresh 토큰 삭제
    */

    @PostMapping("/member/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request , HttpServletResponse response ) {
        try {
            //1) 요청의 유효성을 판단하여 , 유효하던 유효하지 않던간에 무조건 로그아웃에 대한 요청 처리
            //결과는 동일한 '성공' 으로 응답하자.
            //AccessToken , deviceId
            if (request == null || !StringUtils.hasText(request.getAccessToken()) || !StringUtils.hasText(request.getDeviceId())) {
                return ResponseEntity.ok(Map.of("result", "로그아웃 성공"));
            }

            //클라이언트가 전송한 AccessToken 에서 정보 꺼내기
            Jws<Claims> jws = jwtUtil.parseToken(request.getAccessToken());
            Claims claims = jws.getBody();

            //redis에 블랙 리스트 등록
            //redisTokenService
            String username = jwtUtil.getUsername(claims);
            String jti = jwtUtil.getJti(claims);
            //현재 시간과 JWT가 보유한 유효기간을 구해서 남은 TTL 구하기
            long exp =  claims.getExpiration().toInstant().getEpochSecond();
            long now = Instant.now().getEpochSecond();

            //결과가 양수라면 - 만료 까지 남은 초
            // 결과가 음수라면 - 남은 시간이 없음 , 즉 만료...
            long ttl = Math.max(0, exp-now);

            //redis에 블랙리스트에 등록
            redisTokenService.registBlackList(jti,ttl);

            //redis에서 refreshtoken 삭제
            redisTokenService.deleteRefreshToken(username,request.getDeviceId());

            //쿠키 삭제
            CookieUtil.clearRefreshCookie(response);
            return ResponseEntity.ok(Map.of("result","로그아웃 성공"));
        }catch (Exception e){
            return ResponseEntity.ok(Map.of("result","로그아웃 성공(token invalid)"));
        }
    }

}
