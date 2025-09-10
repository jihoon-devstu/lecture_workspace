package com.sinse.jwtredis.dto;

import lombok.Data;

@Data
public class MemberDTO {

    private int member_id;
    private String id;
    private String password;
    private String name;
    private String email;
    private String code;
    private String deviceId; //유저가 사용중인 디바이스의 고유값(디바이스마다 달라야 함.)
}
