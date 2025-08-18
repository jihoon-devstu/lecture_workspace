package com.sinse.chatroomapp.service;

import com.sinse.chatroomapp.domain.Member;

import java.util.List;

public interface MemberService {
    public List<Member> selectAll();
    public Member selectById(int member_id);
    public void insert(Member member);
    public void update(Member member);
    public void delete(int member_id);
    public Member login(Member member);
}
