package com.hyunwjd.umc9th.domain.review.converter;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.dto.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.dto.ReviewResDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.store.entity.Store;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

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

        // result -> DTO
        public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
                Page<Review> result
        ){
            return ReviewResDTO.ReviewPreViewListDTO.builder()
                    .reviewList(result.getContent().stream()
                            .map(ReviewConverter::toReviewPreviewDTO)
                            .toList()
                    )
                    .listSize(result.getSize())
                    .totalPage(result.getTotalPages())
                    .totalElements(result.getTotalElements())
                    .isFirst(result.isFirst())
                    .isLast(result.isLast())
                    .build();
        }

        public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
                Review review
        ){
            return ReviewResDTO.ReviewPreViewDTO.builder()
                    .ownerNickname(review.getMember().getName())
                    .score(review.getStarGrade())
                    .body(review.getText())
                    .createdAt(LocalDateTime.from(review.getCreatedAt()))
                    .build();
        }
    }


