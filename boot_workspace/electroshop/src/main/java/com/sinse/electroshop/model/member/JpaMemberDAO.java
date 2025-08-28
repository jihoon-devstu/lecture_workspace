package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaMemberDAO implements MemberDAO {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Member login(Member member) {
        return jpaMemberRepository.findByIdAndPassword(member.getId(), member.getPassword());
    }
}
