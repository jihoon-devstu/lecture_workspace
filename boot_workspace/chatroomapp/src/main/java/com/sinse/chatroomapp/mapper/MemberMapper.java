package com.sinse.chatroomapp.mapper;

import com.sinse.chatroomapp.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<Member> selectAll();
    public Member selectById(int member_id);
    public void insert(Member member);
    public void update(Member member);
    public void delete(int member_id);
    public Member login(Member member);
}
