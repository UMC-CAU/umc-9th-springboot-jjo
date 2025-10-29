package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 기본 정보 조회
    Optional<Member> findById(Long memberId);

}
