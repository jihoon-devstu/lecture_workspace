package com.sinse.customlogindb.util;

import com.sinse.customlogindb.domain.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
* 로그인 검증이 성공 되면 Authentication 객체가 생성되고 , 이 객체에
* UserDetails 객체가 등록된다
* 로그인에 성공한 사용자 정보를 보유한 객체
* 스프링 시큐리티 프레임워크는 회원 정보를 담은 객체가 어떤 클래스인지 예측할 수 없다.
* 따라서 userDetails 라는 이름의 인터페이스를 미리 정해놓았다.
* 개발자가 이 인터페이스의 메서드에 회원정보를 채워 넣기를 원한다...
* 그래야 개발자 대신 Id와 Password를 이용하여 DB검증을 들어간다.
* */

@Data
public class CustomUserDetails implements UserDetails {

    private Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
