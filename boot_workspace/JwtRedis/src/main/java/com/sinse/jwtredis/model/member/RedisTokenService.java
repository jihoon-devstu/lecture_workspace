package com.sinse.jwtredis.model.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


/*
    [Redis 3.x 기반 토큰 상태 관리]
    1) 로그아웃 한 유저를 어떤 방식으로 블랙리스트에 저장할 것인지 키값에 대한 설계
        bl:access:<Jti>
    2) 사용자의 모든 디바이스로부터 로그아웃을 수행하기 위한 버전 키값에 대한 설계
        uv:<userId>
    3) access 토큰과 함께 발급되는 refresh 토큰을 저장하기 위한 키값에 대한 설계
        rt:<userId>
 */

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final StringRedisTemplate redis;

    /* 사용자 버전 조회 (버전이 없으면 0 을 반환)

    사용자 별로 관리하는 버전 번호를 조회하는 용도의 메서드 정의
    참고 ) 버전을 사용하는 이유 ?
        JWT + Redis 환경에서 블랙리스트만으로는 충분하지 않을 때가 있음
        예를들어 , 회원이 로그아웃 하거나 비밀번호를 바꿨을 때 ,
        기존에 발급된 AccessToken 및 RefreshToken 을 모두 무효화 시켜야 함.
        이때 , 토큰 Payload (실어진 내용) 에 Ver:1 과 같은 사용자 버전을 넣어두고
        Redis에서 "uv:userId 2" 로 메모리에 올려두면 , 토큰의 페이로드에 갖고 있는
        버전1은 Redis보다 낮으므로 , 낮은 버전을 가진 토큰은 전부 무효 처리 가능.
        [블랙리스트와 차이점]
        블랙리스트 - 단일 유저 로그아웃 / 토큰 단위로 차단하는 방식 , 만료될 때 까지 redis 에 개별 키 보관.
        버전 -  사용자 단위로 이 사용자가 사용중인 모든 디바이스까지 한꺼번에 토큰을 무효화
     */

    public int currentUserVersion(String username){
        String v = redis.opsForValue().get("uv:"+username);
        //위 코드에 의해 redis 내부에서는 GET uv:<username> 가 수행됨
        //해당 키가 존재할 경우 저장된 문자열을 그대로 반환하지만 , 그렇지 않으면 0 반환
        //StringRedisTemplage 객체는 redis가 반환한 nul을 java의 null로 변환해줌.

        return (v==null) ? 0 : Integer.parseInt(v);
    }

    // Access Token 을 블랙리스트 등록
    //ttlSeconds 토큰에 남은 잔여 시간 등록
    public void registBlackList(String jti, long ttlSeconds){
        //TTL이 이미 만료된 시간인 경우엔 블랙리스트 등록할 필요조차 업다
        if(ttlSeconds<=0){
            return;
        }
        // SETEX bl:access:jti
        redis.opsForValue().set("bl:access"+jti, "1",Duration.ofSeconds(ttlSeconds));
    }

    //블랙리스트 조회
    public boolean isBlackList(String jti){
        Boolean exist = redis.hasKey("bl:access"+jti);
        // 위의 hasKey 메서드에 의해서 수행되는 Redis 명령어는
        // exists bl:access:abc123
        // -> 0인 경우엔 false (존재하지 않음) , 1인 경우엔 true (존재함)

        return exist !=null && exist;
    }


}
