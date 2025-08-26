package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByIdAndPassword(String id, String password);
}
