package com.toj.repository;

import com.toj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select MAX(m.id) " +
            "from Member m")
    Optional<Long> findMaxMemberId();
}
