package com.sinse.jwtredis.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/*
* JWT 발급 / 검증 전용 유틸
* 1) AccessToken : 인증을 받았음을 증명하는 용도의 토큰 , 즉 로그인 성공 결과 토큰
*                           유효한 AccessToken을 보유한 클라이언트는 어떤 자원이든 통과
* 2) RefreshToken : 보안상 만료시간 (Time To Leave) 이 제한되어있는 AT 을 재발급 할 때
*                           검증 용도로 사용할 토큰을 의미함. RT
*
*   용어 ) Claim (클레임) = JWT 를 구성하는 내용 (sub - userId , JTI - 키의 고유값(UUID) , ver , deviceId 등)
*
* */
@Component
public class JwtUtil {
    // 서명에 사용할 비밀번호 , 비밀 키 (HMAC - SHA)
    private final SecretKey key;

    //발급자 (토큰 생성자 - 앱의 이름)
    private final String issure;

    //Access Token 만료 기간(분)  - ex) 5분 ~ 15분
    private final long accessMinutes;

    //Refresh Token 만료 기간(일) - ex) 7일 ~ 14일
    private final long refreshDays;

    public JwtUtil(@Value("${app.jwt.issure}") String issure,
                   @Value("${app.jwt.secret}") String secret,
                   @Value("${app.jwt.access-minutes}") long accessMinutes,
                   @Value("${app.jwt.refresh-days}")  long refreshDays) {
        this.issure = issure;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessMinutes = accessMinutes;
        this.refreshDays = refreshDays;
    }


    /* -------------------------------------------------------------------------------------------------
        Access Token 생성
    -------------------------------------------------------------------------------------------------*/

    public String createAccessToken(String username , int userVersion , String deviceId){
        Instant now = Instant.now(); //현재 시간
        Instant exp = now.plusSeconds(accessMinutes*60); //만료 시각

        return Jwts.builder()
                .setIssuer(issure)
                .setSubject(username) //로그인 유저 Id
                .setId(UUID.randomUUID().toString()) //토큰의 고유 Id
                .setIssuedAt(Date.from(now)) //생성일
                .setExpiration(Date.from(exp))//만료일

                //개발자가 토큰에 넣고 싶은 내용
                //추후 로그아웃 처리에 필요한 내용
                .claim("ver", userVersion) //버전을 이용한 로그아웃
                .claim("deviceId", deviceId) //로그아웃 처리할 디바이스

                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /* -------------------------------------------------------------------------------------------------
        Refresh Token 생성
        생명력이 길기 때문에 , 보안상 민감하므로 절대 유출되면 안됨.
        만일 유출 되면 , 재발급 ! 보안상 위험성을 낮추자...
    -------------------------------------------------------------------------------------------------*/
    public String createRefreshToken(String username, String deviceId){

        Instant now = Instant.now(); //현재 시간
        Instant exp = now.plusSeconds(refreshDays*24*60*60); //만료 시각

        return Jwts.builder()
                .setIssuer(issure)
                .setSubject(username) //로그인 유저 Id
                .setId(UUID.randomUUID().toString()) //토큰의 고유 Id
                .setIssuedAt(Date.from(now)) //생성일
                .setExpiration(Date.from(exp))//만료일

                //개발자가 토큰에 넣고 싶은 내용
                .claim("deviceId", deviceId) //리프레쉬 토큰 발급할 디바이스 Id

                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /* -------------------------------------------------------------------------------------------------
        토큰 파싱 및 검증
        1) 조작되지 않았는가
        2) 서명
        3) 만료기간
    -------------------------------------------------------------------------------------------------*/

    public Jws<Claims> parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
    /* -------------------------------------------------------------------------------------------------
        토큰의 내용 중 필요한 값 추출 도우미
    -------------------------------------------------------------------------------------------------*/
    //유저네임 (id)
    public String getUsername(Claims c){
        return c.getSubject();
    }

    //JWT 고유 id
    public String getJti(Claims c){
        return c.getId();
    }

    // Access Token 의 버전
    public int getVersion(Claims c){
        Object v =  c.get("ver");
        return (v==null) ? 0 : (Integer)v;
    }

    //Device Id
    public String getDeviceId(Claims c){
        Object v =  c.get("deviceId");
        return (v==null) ? "" : v.toString();
    }

    //유효기간
    public long getExpireTime(Claims c){
        Date d = c.getExpiration();
        return (d==null) ? 0L : d.toInstant().getEpochSecond();
    }


}
