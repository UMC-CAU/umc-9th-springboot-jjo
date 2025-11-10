package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // [조회] 회원 단일 조회 (memberId로)
    Optional<Member> findById(Long memberId);
    //^^^^^^존재하는 회원인지 검증하기 위해 Optional로 감싸서 반환

    // [조회] 회원 + 미션 + 포인트 통계 조회
    @Query("""
        SELECT m
        FROM Member m
        LEFT JOIN FETCH m.memberMissions mm
        LEFT JOIN FETCH mm.mission ms
        WHERE m.id = :memberId
        """)
    Optional<Member> findWithMissionsById(@Param("memberId") Long memberId);

    // [조회] 회원 미션 완료 통계 조회 (완료한 미션 개수, 완료한 미션 개수의 10으로 나눈 나머지)
    @Query("""
    SELECT COUNT(DISTINCT mm.mission.id),
           MOD(COUNT(DISTINCT mm.mission.id), 10)
    FROM MemberMission mm
    WHERE mm.member.id = :memberId
      AND mm.isComplete = true
""")
    Object[] findCompletionStatsByMember(@Param("memberId") Long memberId);

    // [검증] 이메일 존재여부 검사
    boolean existsByEmail(String email);
}
