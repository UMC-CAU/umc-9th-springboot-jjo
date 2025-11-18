package com.hyunwjd.umc9th.domain.member.dto.res;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MemberResDTO {
    @Getter
    @Builder
    public static class MemberInfo {
        private Long memberId; //추후에 인증 방식 도입하면 없어질 예정(?)
        private String phoneNumber;
        private String email;
        private String nickname;
        private Integer point;
    }

    @Getter
    @Builder
    public static class MemberMissionList {
        private Long missionId;
        private String complete_condition; //= 미션 내용
        private String store_name; //= 가게 이름
        private LocalDateTime deadline; //= 미션 마감일
        private boolean isComplete;
        private LocalDateTime completedAt;

        private LocalDate createdAt;
        private LocalDate updateAt;
    }

    @Getter
    @Builder
    public static class MemberMissionStats {
            private Long completedCount;       // 완료 미션 개수 (ex. 10)
            private Long missionCompletionRate; // 미션 달성도
    }


}
