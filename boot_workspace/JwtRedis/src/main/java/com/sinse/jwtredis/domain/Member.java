package com.sinse.jwtredis.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="member")
@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_id;

    private String id;
    private String password;
    private String name;
    private String email;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

    @PrePersist
    public void prePersist() {
        if (role == null) {
            Role defaultRole = new Role();
            defaultRole.setRole_id(3); // PK만 세팅하면 됨 (프록시로 취급)
            this.role = defaultRole;
        }
    }
}
