package com.hyunwjd.umc9th.domain.review.converter;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.dto.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.dto.ReviewResDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.store.entity.Store;

public class ReviewConverter {
    // Create DTO -> Review 엔티티
    public static Review toReview(ReviewReqDTO.CreateReview dto, Store store, Member member) {
        Review review = Review.builder()
                .text(dto.getText())
                .starGrade(dto.getStarGrade())
                .store(store)
                .member(member)
                .build();

        return review;
    }

    // Review 엔티티 -> CreateResult DTO
    public static ReviewResDTO.CreateResult toCreateResult(Review review) {

        return ReviewResDTO.CreateResult.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .starGrade(review.getStarGrade())
                .text(review.getText())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
