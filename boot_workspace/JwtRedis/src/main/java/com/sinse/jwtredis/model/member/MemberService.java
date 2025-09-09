package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.MemberDTO;

public interface MemberService {
    public void regist(Member member);
}
