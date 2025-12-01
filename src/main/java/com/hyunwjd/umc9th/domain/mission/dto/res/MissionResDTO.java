package com.hyunwjd.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MissionResDTO {
    private Long missionId;
    private String completeCondition;  // 미션 제목/설명
    private String storeName;          // 가게 이름
    private boolean isComplete;        // 진행 여부
    private LocalDateTime completedAt;     // 완료된 경우만 값, 아니면 null
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
