package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;
import com.sinse.customlogindb.util.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberDAO memberDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("아이디로 회원 정보 조회 시도");

        Member member = memberDAO.getMemberById(username);
        if(member == null){
            throw new UsernameNotFoundException(username);
        }

        //만일 회원이 존재하면 , 스프링이 그 회원의 정보를 알아야 하므로
        return new CustomUserDetails(member);
    }
}
