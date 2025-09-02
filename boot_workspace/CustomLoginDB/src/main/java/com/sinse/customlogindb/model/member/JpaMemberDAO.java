package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaMemberDAO implements MemberDAO{

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public List<Member> selectAll() {
        return jpaMemberRepository.findAll();
    }

    @Override
    public Member select(int memberId) {
        return jpaMemberRepository.findById(memberId).get();
    }

    @Override
    public void insert(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        jpaMemberRepository.delete(member);
    }

    @Override
    public void update(Member member) {
    }

    @Override
    public Member getMemberById(String id) {

        return jpaMemberRepository.findById(id);

    }
}
