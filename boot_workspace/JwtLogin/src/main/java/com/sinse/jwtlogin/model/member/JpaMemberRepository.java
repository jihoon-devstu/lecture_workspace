package com.sinse.jwtlogin.model.member;

import com.sinse.jwtlogin.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Integer> {
    public Member findById(String id);
}
