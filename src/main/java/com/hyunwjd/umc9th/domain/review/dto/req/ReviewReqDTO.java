package com.hyunwjd.umc9th.domain.review.dto.req;

import lombok.*;

import java.util.List;

public class ReviewReqDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateReview {

        private Integer starGrade;

        private String text;

        // 사진 URL 리스트 (선택)
        private List<String> photoUrls;
    }
}
