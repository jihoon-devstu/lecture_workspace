package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistServiceImpl implements RegistService{

    private final RegistRedisService registRedisService;

    @Override
    public void regist(MemberDTO memberDTO) {
        memberDTO.setCode(registRedisService.generateCode6());

        registRedisService.savePending(memberDTO);
    }
}
