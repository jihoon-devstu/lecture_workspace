package com.sinse.electroshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Mysql 의 auto_increment 매핑
    @Column(name="member_id")
    private int memberId;
    private String id;
    private String password;
    private String name;
}
