package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final JpaMemberRepository jpaMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void regist(Member member) {

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        Member obj = jpaMemberRepository.save(member);

        if (obj == null) {
            throw new RuntimeException("회원 등록 실패");
        }
    }


}
