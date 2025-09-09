package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = jpaMemberRepository.findById(username);
        if(member == null) {
            throw new UsernameNotFoundException("일치하는 회원이 존재하지 않습니다");
        }


        return new CustomUserDetails(member);
    }
}
