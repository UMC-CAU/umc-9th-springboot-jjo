package com.hyunwjd.umc9th.domain.mission.dto.res;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MissionResDTO {
    // 1) 미션 리스트에서 개별 미션 Preview 정보
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private String completeCondition;
        private String storeName;
        private boolean isComplete;
        private LocalDateTime completedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    // 2) 미션 목록 조회 (페이징 포함)
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missionList;
        private int listSize;
        private int totalPage;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }

    // 3) 진행 완료로 상태 변경 결과 상세 정보
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDTO {
        private Long missionId;
        private String completeCondition;
        private String storeName;
        private boolean isCompleted;
        private LocalDateTime completedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
