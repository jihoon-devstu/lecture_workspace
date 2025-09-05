package com.sinse.jwtlogin.security;

import com.sinse.jwtlogin.filter.JwtAuthFilter;
import com.sinse.jwtlogin.model.member.CustomUserDetailsService;
import com.sinse.jwtlogin.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public OncePerRequestFilter jwtAuthFilter(JwtUtil jwtUtil) {
        return new JwtAuthFilter(jwtUtil,customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //서비스를 이용하여 user 정보를 가져와야 하므로 , 서비스 등록
        provider.setUserDetailsService(customUserDetailsService);

        //사용하게 될 비밀번호 인코더
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OncePerRequestFilter jwtAuthFilter, DaoAuthenticationProvider provider) throws Exception {
        http
                //REST/JWT 사용시 비활성화
                .csrf(csrf -> csrf.disable())

                //세션을 만들지도 , 사용하지도 않도록 정책을 STATELESS로 설정
                //시큐리티가 인증 상태를 세션에 더이상 자동으로 저장하지 않게 함.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //securitycontext 저장소를 null 저장소로 교체 , 즉 인증 결과를 세션 / 서버 상태에 저장하지 않음
                //완전 무상태를 보장하기 위함 !
                .securityContext(sc->sc.securityContextRepository(new NullSecurityContextRepository()))

                //시큐리티의 폼 로그인 기능을 이용하면 , 컨트롤러 없이도 로그인 인증이 처리되지만,
                //JWT 에서는 개발자가 토큰을 컨트롤러에서 발급을 해야 하므로 , 컨트롤러를 활성화 시키고
                // 대신 form 로그인 기본 기능을 비활성화
                .formLogin(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/member/login.html").permitAll()
                        .requestMatchers("/member/login").permitAll()
                        .anyRequest().authenticated()
                )

                .authenticationProvider(provider)
                .addFilterBefore( jwtAuthFilter , AuthorizationFilter.class);

        return http.build();
    }
}
