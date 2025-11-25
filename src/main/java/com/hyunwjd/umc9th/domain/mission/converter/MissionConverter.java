package com.hyunwjd.umc9th.domain.mission.converter;

import com.hyunwjd.umc9th.domain.mission.dto.res.MissionResDTO;
import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.store.entity.Store;

public class MissionConverter {
    public static MissionResDTO toMissionResDTO(MemberMission mm) {
        Mission mission = mm.getMission();
        Store store = mission.getStore();

        return MissionResDTO.builder()
                .missionId(mission.getId())
                .completeCondition(mission.getCompleteCondition())
                .storeName(store.getName())
                .isComplete(mm.isComplete())
                .completedAt(mm.getCompletedAt() != null ? mm.getCompletedAt().toLocalDate() : null)
                .createdAt(mm.getCreatedAt())
                .updatedAt(mm.getUpdatedAt())
                .build();
    }
}
