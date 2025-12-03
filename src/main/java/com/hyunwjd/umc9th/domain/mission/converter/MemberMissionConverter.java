package com.hyunwjd.umc9th.domain.mission.converter;
import com.hyunwjd.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import lombok.*;

public class MemberMissionConverter {
    public static MemberMissionResDTO.ChallengeResult toChallengeResult(MemberMission mm) {
        return MemberMissionResDTO.ChallengeResult.builder()
                .memberMissionId(mm.getId())
                .memberId(mm.getMember().getId())
                .missionId(mm.getMission().getId())
                .storeId(mm.getMission().getStore().getId())
                .isComplete(mm.isCompleted())
                .completedAt(mm.getCompletedAt())
                .createdAt(mm.getCreatedAt())
                .build();
    }
}
