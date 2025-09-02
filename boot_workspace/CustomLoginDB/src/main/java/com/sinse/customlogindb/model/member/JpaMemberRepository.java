package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository <Member, Integer>{
    public Member findByPassword(String password);
    public Member findById(String id);
}
