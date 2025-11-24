package com.hyunwjd.umc9th.domain.mission.dto.res;

import lombok.*;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChallengeResult {
        private Long memberMissionId;
        private Long memberId;
        private Long missionId;
        private Long storeId;
        private boolean isComplete;
        private LocalDateTime completedAt;
        private LocalDateTime createdAt;
    }
}
