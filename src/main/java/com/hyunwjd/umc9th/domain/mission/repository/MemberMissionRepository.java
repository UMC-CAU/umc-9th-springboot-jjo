package com.hyunwjd.umc9th.domain.mission.repository;

import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 미션 조회
    @Query("""
    SELECT 
        COUNT(m),
        SUM(CASE WHEN m.isCompleted = true THEN 1 ELSE 0 END)
    FROM MemberMission m
    WHERE m.member.id = :memberId
""")
    Object[] getMissionStats(@Param("memberId") Long memberId);

    // 미션 도전
    boolean existsByMember_IdAndMission_Id(Long memberId, Long missionId);

    // 미션 완료
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
}

