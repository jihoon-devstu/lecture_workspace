package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.MemberDTO;
import lombok.RequiredArgsConstructor;


public interface RegistService {
    public void regist(MemberDTO memberDTO);
}
