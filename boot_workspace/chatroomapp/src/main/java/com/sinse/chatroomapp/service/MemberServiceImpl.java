package com.sinse.chatroomapp.service;

import com.sinse.chatroomapp.dao.MemberDAO;
import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    MemberDAO memberDAO;
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }


    @Override
    public List<Member> selectAll() {
        return memberDAO.selectAll();
    }

    @Override
    public Member selectById(int member_id) {
        return memberDAO.selectById(member_id);
    }

    @Override
    public void insert(Member member) {
        memberDAO.insert(member);
    }

    @Override
    public void update(Member member) {
        memberDAO.update(member);
    }

    @Override
    public void delete(int member_id) {
        memberDAO.delete(member_id);
    }

    @Override
    public Member login(Member member) throws MemberException {
        return memberDAO.login(member);
    }
}
