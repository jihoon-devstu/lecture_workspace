package com.sinse.jwtredis.security;

import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.model.member.MemberDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.NullSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //개발자가 정의한 컨트롤러에서 AuthenticationManager 를 사용할 예정이므로 , 미리 등록
    //만일 개발자가 필요한 시점에 new를 해버리면 , 스프링이 관리하는 Bean이 아니게 됨...
    //따라서 @Bean으로 등록

    @Bean
    public AuthenticationManager authenticationManager(MemberDetailsService mdService, PasswordEncoder passwordEncoder) throws Exception {
        //AuthenticationManager는 DaoAuthenticationProvider를 통해
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //1. 유저 얻기 (by id)
        provider.setUserDetailsService(mdService);
        //2. 비밀번호 검증 (using PasswordEncoder)
        provider.setPasswordEncoder(passwordEncoder);

        return  new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //JWT 기반이므로 CSRF 토큰 불필요
                //세션 기반 사용하지 않음.
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //시큐리티에게 인증 받은 회원 직접 알려줄게.
                .securityContext(sc->sc.requireExplicitSave(false))

                //스프링 디폴트 폼 로그인 비활성화
                .formLogin(form -> form.disable())

                //스프링 기본 로그아웃 비활성화
                .logout(logout -> logout.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/member/regist.html" , "/member/login" , "/member/login.html","/member/regist", "/member/refresh" , "/member/logout").permitAll()
                        .anyRequest().authenticated() //이외 요청은 로그인을 해야만 통과
                ).build();

    }
}
