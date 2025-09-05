package com.sinse.jwtlogin.filter;

//클라이언트의 요청을 처리하는 모든 컨트롤러에서 각각 Jwt의 유효성을 체크하기 보다는
//요청의 입구에섣 ㅗㅇ작하는 필터 차원에서 JWT의 유효성을 체크해보자.
//단 , javaEE의 일반적인 필터가 아니라 , Spring Security에서 지원되는 필터를 개발자가 커스텀해야한다...

import com.sinse.jwtlogin.domain.CustomUserDetails;
import com.sinse.jwtlogin.model.member.CustomUserDetailsService;
import com.sinse.jwtlogin.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

// 하나의 요청 마다 단 한번만 ㅅ힐행되는 필터
// 예를들어 DispatcherServlet 앞뒤에 여러 필터 체인이 있더라도
//이 필터가 여러번 호출되지 않도록 보장함
//JWT 토큰 검증 시 , 검증은 한번만 실행되면 충분한 작업이므로...
//이 객체는 필수인가?? 선택이지만 , 만약 이 필터를 사용하지 않을 경우
//모든 컨트롤러의 메서드 마다 회원 인증이 필요한 서비스의 경우 JWT 인증 코드가 중복된다...
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("OncePerRequestFilter 로 구현한 검증 필터 동작함");

        //클라이언트가 Authorization의 Bearer 에 함께 보낸 Token 을 검증

        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            //토큰 검증...
            if(jwtUtil.validateToken(token)){
                //사용자 정보 중 username 추출
                String username = jwtUtil.getUsername(token);
                log.debug("토큰으로 부터 추출한 사용자 정보는"+username);

                CustomUserDetails user = (CustomUserDetails)customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(Map.of("error","토큰이 유효하지 않습니다"));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
