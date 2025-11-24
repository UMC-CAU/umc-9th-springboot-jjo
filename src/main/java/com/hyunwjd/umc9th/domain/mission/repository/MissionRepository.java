package com.hyunwjd.umc9th.domain.mission.repository;

import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // [조회] 회원의 전체 미션 조회
    Page<MemberMission> findByMemberId(Long memberId, Pageable pageable);

    // [조회] 진행 중 / 진행 완료된 미션 목록 조회
    Page<MemberMission> findByMemberIdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);


    // [조회] 현위치에서 도전 가능한 미션 목록 조회 (10개씩)
    @Query("""
        SELECT COUNT(DISTINCT mm.mission.id),
               MOD(COUNT(DISTINCT mm.mission.id), 10)
        FROM MemberMission mm
        WHERE mm.member.id = :memberId
          AND mm.isComplete = true
        """)
    Object[] findCompletionStatsByMember(@Param("memberId") Long memberId);


}
