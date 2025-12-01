package com.hyunwjd.umc9th.domain.review.dto;

import com.hyunwjd.umc9th.domain.review.entity.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 생성 결과 응답용 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResult {
        private Long reviewId;
        private Long storeId;
        private Long memberId;
        private Float starGrade;
        private String text;
        private LocalDateTime createdAt;
    }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDateTime createdAt
    ){}
}
