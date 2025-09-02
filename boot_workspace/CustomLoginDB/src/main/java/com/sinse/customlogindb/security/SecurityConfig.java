package com.sinse.customlogindb.security;


import com.sinse.customlogindb.model.member.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberDetailsService memberDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //AuthenticationProvider 추가
    //개발자 대신 비밀번호를 비교 검증 (따라서 개발자는 Repository , DAO 등에서 password를 사용할 필요가 없음.)
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(memberDetailsService); //어떤 서비스를 이용할지
        provider.setPasswordEncoder(passwordEncoder()); //어떤 비밀번호 변환 객체를 이용할지
        return  provider;
    }

    //AuthenticationManager 추가
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    //스프링 시큐리티의 처리를 담당하는 객체인 SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/loginform").permitAll()
                        .anyRequest().authenticated())
                        .formLogin(form->form
                                .loginPage("/loginform") //로그인 폼을 만나기 위한 요청 주소 등록
                                .loginProcessingUrl("/login") // 로그인 요청을 처리하는 uri 등록
                                .usernameParameter("id")
                                .passwordParameter("pwd")
                                .defaultSuccessUrl("/main",true)
                                .permitAll()
                        ); //이 어플리케이션에선 모든 요청에 로그인이 되어 있는지 검증한다.

        return http.build();
    }

}
