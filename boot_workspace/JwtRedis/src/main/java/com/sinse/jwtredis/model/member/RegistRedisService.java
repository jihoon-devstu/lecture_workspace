package com.sinse.jwtredis.model.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistRedisService {

    private final StringRedisTemplate redis;
    private final SecureRandom random = new SecureRandom();
    private final ObjectMapper objectMapper = new ObjectMapper();

    //TTL 설정 (Time To Leave)
    //임시 가입 인증 만료 시간 3분.
    private static final Duration PENDING_TTL = Duration.ofMinutes(3);

    //키 설계 (redis에 사용될 키 규칙 - 개발자가 정하는 것임)
    private String pendingKey(String email){
        return "pending:" + email; //pending:email@naver.com
    }

    //이메일을 조회하기 위한 키 설계 (모아둬야 select *from ~~ 와 동일한 효과 가능)
    private String codeKey(String code){
        return "code:" + code;
    }

    //이메일 인증코드 생성 , 6자리
    public String generateCode6(){
        return String.format("%06d",random.nextInt(1_000_000));
    }

    //임시 가입 (redis에 쓰기)
    //Member 라는 java 객체가 redis 로 insert 되려면 , 문자열화 되어야함.
    public void savePending(MemberDTO memberDTO){
        try {
            String json=objectMapper.writeValueAsString(memberDTO);

            //회원 임시 정보
            redis.opsForValue().set(pendingKey(memberDTO.getEmail()),json, PENDING_TTL);

            //이메일을 찾을 수 있도록 인덱스 생성
            redis.opsForValue().set(codeKey(memberDTO.getCode()),memberDTO.getEmail(), PENDING_TTL);

            log.debug("Redis에 등록된 이메일의 검색용 구분 코드는 : "+memberDTO.getCode());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
