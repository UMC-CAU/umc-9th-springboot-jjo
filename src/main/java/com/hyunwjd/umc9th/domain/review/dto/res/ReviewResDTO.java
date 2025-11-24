package com.hyunwjd.umc9th.domain.review.dto.res;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateResult {
        private Long reviewId;
        private Long storeId;
        private Long memberId;
        private Integer starGrade;
        private String text;
        private List<String> photoUrls;
        private LocalDateTime createdAt;
    }
}
