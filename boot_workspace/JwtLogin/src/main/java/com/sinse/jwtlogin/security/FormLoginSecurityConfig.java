package com.sinse.jwtlogin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.jwtlogin.model.member.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class FormLoginSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //스프링 시큐리티가 기본적으로 CRSF 방지 기능이 있음. 아래의 코드로 disable 시키면
        //CSRF를 비활성화 시킴. - 전통적인 form 로그인/세션 방식이 아니라 REST / JWT 로 인증 처리할때는 보통 끔.
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/member/login.html")
                        .loginProcessingUrl("/member/login")
                        //.defaultSuccessUrl("/index.html") //로그인 성공 후 보여질 url

                        //비동기 방식의 요청이 들어올 때는 , json으로 결과를 보여줘야 하므로,
                        //컨트롤러를 작성하지 않고도 json 결과를 전송할 수 있다..
                        .successHandler((request, response, auth) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json;charset=UTF-8");

                            //Map.of("","","",) 값 고정이므로 , 생성 후 변경 불가.

                            Map<String, String> map = new HashMap<>();
                            map.put("result", "로그인 성공");
                            map.put("user", auth.getName());

                            response.getWriter().print(objectMapper.writeValueAsString(map));
                        })
                        .failureHandler((request, response, ex) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");

                            Map<String, String> map = new HashMap<>();
                            map.put("result", "로그인 실패");
                            map.put("message", ex.getMessage());

                            response.getWriter().print(objectMapper.writeValueAsString(map));
                        })
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return null;
    }

    //현재 DB에 들어간 비밀번호는 BCrypt 알고리즘이므로 , 시큐리티에게 같은 알고리즘을 사용하도록 알려줘야함.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
