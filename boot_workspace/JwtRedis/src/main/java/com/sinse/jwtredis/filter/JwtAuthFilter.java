package com.sinse.jwtredis.filter;



import com.sinse.jwtredis.model.member.RedisTokenService;
import com.sinse.jwtredis.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


/*
    매 요청마다 실행되는 인증 필터
    1) 클라이언트의 헤더에 들어있는 Authrozation 에서 Bearer 토큰 추출
    2) JWT 서명 조작 여부 , 만료 기간 검증 (유효한 토큰인지 조사)
    3) 블랙리스트 여부 검증 (이미 로그 아웃한 유저인 경우 401로 전송)
    4) 문제 없으면 , 스프링 시큐리티에게 인증을 알려야 함.
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RedisTokenService redis;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            /* ---------------------------------------------------------------
            1) Authorization 헤더 추출
            --------------------------------------------------------------- */
            String header = request.getHeader("Authorization");

            //넘어온 헤더값이 정상적이라면
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7); //문자열의 index 7번째 부터 끝까지 가져오기.

                /* ---------------------------------------------------------------
                    2) JWT 파싱 및 벨리데이션
                --------------------------------------------------------------- */
                Jws<Claims> jws = jwtUtil.parseToken(token);
                Claims claims = jws.getBody();

                /* ---------------------------------------------------------------
                    3) Claims 로 부터 필요한 값을 추출
                --------------------------------------------------------------- */
                String jti = jwtUtil.getJti(claims); //JTI - UUID 고유값
                String username = jwtUtil.getUsername(claims); //sub - 로그인 사용자
                int version = jwtUtil.getVersion(claims); //ver - 디바이스에 대한 전역적 토큰 버전

                /* ---------------------------------------------------------------
                    4) Redis에서 블랙리스트 여부 판단
                --------------------------------------------------------------- */
                if(redis.isBlackList(jti)){
                    //블랙리스트 인물 이라면 401 처리
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //권한없음
                    return;
                }

                //사용자 전역적 버전 검증 (전역 로그 아웃 구현할 수 있음.)
                int currentVer = redis.currentUserVersion(username);
                if(currentVer != version){
                    //버전 불일치로 인한 401 처리
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                //스프링에게 이 요청이 지금 인증을 성공했음을 알려줘야함 !!!
                //아래의 코드를 처리해야 .anyRequest().authenticated() 를 무사히 통과
                /*
                * [ details 와 principal의 차이점 ]
                *  principal : 누가 로그인 했는가 !! userDetails , 또는 user
                * credential : 어떻게 로그인 했는가 !! 비밀번호 ? JWT ? 등등...
                * details : 부가적인 요청 정보 !! IP , Session Id , User-Agent 같은 환경 정보.
                * 
                * 
                * */
                UsernamePasswordAuthenticationToken auth 
                        = new UsernamePasswordAuthenticationToken(
                                username, 
                                jws, 
                                List.of(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

            //요청 가로 막았으므로 , 다시 요청의 흐름을 정상화 시킴...
            //doFilter를 만난 사람은 정상 로그인 인증을 받은 사람 !
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            //만료된 토큰인 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("{\"error\":\"토큰만료\"}");
        }catch (JwtException e){
            //잘못된 서명 , 포맷 오류 등 기타 관련 예외
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("{\"error\":\"유효하지 않은 토큰\"}");
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("{\"error\":\"인증 에러\"}");
        }

    }




}
