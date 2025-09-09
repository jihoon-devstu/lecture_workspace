package com.sinse.jwtredis.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    public Member getMember() {
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //ROLE_  <-- 권한 접두어.
        return List.of(new SimpleGrantedAuthority("ROLE_"+member.getRole().getRole_name()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    public String getEmail() {
        return member.getEmail();
    }

    public String getRoleName(){
        return member.getRole().getRole_name();
    }
}
