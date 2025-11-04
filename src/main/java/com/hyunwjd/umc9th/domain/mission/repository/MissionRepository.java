package com.hyunwjd.umc9th.domain.mission.repository;

import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 전체(진행중+완료) — memberId 기준
    // 정렬/페이징은 Pageable로
    @EntityGraph(attributePaths = {"mission", "mission.store"}) // 선택: N+1 완화
    Page<MemberMission> findByMember_Id(Long memberId, Pageable pageable);

    // 상태별(진행중 only 또는 완료 only)
    @EntityGraph(attributePaths = {"mission", "mission.store"}) // 선택
    Page<MemberMission> findByMember_IdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);

    interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

        // 위치(location) 내 완료된 미션 수 (COUNT 용)
        long countByMember_IdAndIsCompleteTrueAndMission_Store_Location_Id(Long memberId, Long locationId);

        // (옵션) 나중에 서비스에서 세트 연산용으로 사용할 수 있게 완료 목록도 뽑아둠
        List<MemberMission> findByMember_IdAndIsCompleteTrue(Long memberId);
    }
}
