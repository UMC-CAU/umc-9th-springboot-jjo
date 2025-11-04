package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 1) 회원 단일 조회 (memberId로)
    Optional<Member> findById(Long memberId);

    // 2) 회원 + 미션 + 포인트 통계 조회 (JPQL @Query 방식)
    @Query("""
        SELECT m
        FROM Member m
        LEFT JOIN FETCH m.memberMissions mm
        LEFT JOIN FETCH mm.mission ms
        WHERE m.id = :memberId
        """)
    Optional<Member> findWithMissionsById(@Param("memberId") Long memberId);

    // 3) 회원 존재 여부 확인
    boolean existsByEmail(String email);
}
