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
}
