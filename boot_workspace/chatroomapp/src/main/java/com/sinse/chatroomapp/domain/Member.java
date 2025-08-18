package com.sinse.chatroomapp.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private int member_id;
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime regdate;

}
